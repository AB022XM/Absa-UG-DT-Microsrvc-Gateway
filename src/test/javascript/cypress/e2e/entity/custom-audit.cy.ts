import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('CustomAudit e2e test', () => {
  const customAuditPageUrl = '/custom-audit';
  const customAuditPageUrlPattern = new RegExp('/custom-audit(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const customAuditSample = { actionId: 'X', createdAt: '2023-07-24T23:42:51.784Z' };

  let customAudit;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/custom-audits+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/custom-audits').as('postEntityRequest');
    cy.intercept('DELETE', '/api/custom-audits/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (customAudit) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/custom-audits/${customAudit.id}`,
      }).then(() => {
        customAudit = undefined;
      });
    }
  });

  it('CustomAudits menu should load CustomAudits page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('custom-audit');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('CustomAudit').should('exist');
    cy.url().should('match', customAuditPageUrlPattern);
  });

  describe('CustomAudit page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(customAuditPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create CustomAudit page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/custom-audit/new$'));
        cy.getEntityCreateUpdateHeading('CustomAudit');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/custom-audits',
          body: customAuditSample,
        }).then(({ body }) => {
          customAudit = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/custom-audits+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [customAudit],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(customAuditPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details CustomAudit page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('customAudit');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditPageUrlPattern);
      });

      it('edit button click should load edit CustomAudit page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CustomAudit');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditPageUrlPattern);
      });

      it('edit button click should load edit CustomAudit page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CustomAudit');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditPageUrlPattern);
      });

      it('last delete button click should delete instance of CustomAudit', () => {
        cy.intercept('GET', '/api/custom-audits/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('customAudit').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditPageUrlPattern);

        customAudit = undefined;
      });
    });
  });

  describe('new CustomAudit page', () => {
    beforeEach(() => {
      cy.visit(`${customAuditPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('CustomAudit');
    });

    it('should create an instance of CustomAudit', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('2176e3a9-66e4-41e5-8e92-fe34b9720801')
        .invoke('val')
        .should('match', new RegExp('2176e3a9-66e4-41e5-8e92-fe34b9720801'));

      cy.get(`[data-cy="recordId"]`).type('De-engineered interface PCI').should('have.value', 'De-engineered interface PCI');

      cy.get(`[data-cy="actionId"]`).select('P');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T20:04').blur().should('have.value', '2023-07-25T20:04');

      cy.get(`[data-cy="oldValue"]`).type('Salad Sausages engineer').should('have.value', 'Salad Sausages engineer');

      cy.get(`[data-cy="newValue"]`).type('eyeballs Avon').should('have.value', 'eyeballs Avon');

      cy.get(`[data-cy="changeResaon"]`).type('partnerships').should('have.value', 'partnerships');

      cy.get(`[data-cy="description"]`).type('XSS Keyboard').should('have.value', 'XSS Keyboard');

      cy.get(`[data-cy="description1"]`).type('fuchsia Handcrafted payment').should('have.value', 'fuchsia Handcrafted payment');

      cy.get(`[data-cy="description2"]`).type('Glens Frozen').should('have.value', 'Glens Frozen');

      cy.get(`[data-cy="description3"]`)
        .type('recontextualize superstructure Proactive')
        .should('have.value', 'recontextualize superstructure Proactive');

      cy.get(`[data-cy="description4"]`).type('Agent').should('have.value', 'Agent');

      cy.get(`[data-cy="description5"]`).type('granular Engineer Florida').should('have.value', 'granular Engineer Florida');

      cy.get(`[data-cy="description6"]`)
        .type('International mission-critical International')
        .should('have.value', 'International mission-critical International');

      cy.get(`[data-cy="description7"]`).type('olive Chips Music').should('have.value', 'olive Chips Music');

      cy.get(`[data-cy="description8"]`).type('Planner drive').should('have.value', 'Planner drive');

      cy.get(`[data-cy="description9"]`).type('program').should('have.value', 'program');

      cy.get(`[data-cy="freeText1"]`).type('transmit Kansas').should('have.value', 'transmit Kansas');

      cy.get(`[data-cy="freeText2"]`).type('Salad Yemeni yellow').should('have.value', 'Salad Yemeni yellow');

      cy.get(`[data-cy="freeText3"]`).type('Technician').should('have.value', 'Technician');

      cy.get(`[data-cy="freeText4"]`)
        .type('Rubber infrastructures Team-oriented')
        .should('have.value', 'Rubber infrastructures Team-oriented');

      cy.get(`[data-cy="freeText5"]`).type('Norway Virtual').should('have.value', 'Norway Virtual');

      cy.get(`[data-cy="freeText6"]`).type('Mountain pixel Steel').should('have.value', 'Mountain pixel Steel');

      cy.get(`[data-cy="freeText7"]`).type('Customizable didactic channels').should('have.value', 'Customizable didactic channels');

      cy.get(`[data-cy="freeText8"]`).type('CSS Guiana').should('have.value', 'CSS Guiana');

      cy.get(`[data-cy="freeText9"]`).type('Product Bedfordshire Bedfordshire').should('have.value', 'Product Bedfordshire Bedfordshire');

      cy.get(`[data-cy="freeText10"]`).type('Iowa').should('have.value', 'Iowa');

      cy.get(`[data-cy="freeText11"]`).type('Bedfordshire Investor').should('have.value', 'Bedfordshire Investor');

      cy.get(`[data-cy="freeText12"]`).type('driver').should('have.value', 'driver');

      cy.get(`[data-cy="freeText13"]`).type('HTTP Division').should('have.value', 'HTTP Division');

      cy.get(`[data-cy="freeText14"]`).type('Israel Cambridgeshire Berkshire').should('have.value', 'Israel Cambridgeshire Berkshire');

      cy.get(`[data-cy="freeText15"]`).type('PCI deliver').should('have.value', 'PCI deliver');

      cy.get(`[data-cy="freeText16"]`).type('context-sensitive whiteboard').should('have.value', 'context-sensitive whiteboard');

      cy.get(`[data-cy="freeText17"]`).type('Loan').should('have.value', 'Loan');

      cy.get(`[data-cy="freeText18"]`).type('redundant').should('have.value', 'redundant');

      cy.get(`[data-cy="freeText19"]`).type('Strategist Tools Oklahoma').should('have.value', 'Strategist Tools Oklahoma');

      cy.get(`[data-cy="freeText20"]`).type('dynamic').should('have.value', 'dynamic');

      cy.get(`[data-cy="freeText21"]`).type('compressing reboot').should('have.value', 'compressing reboot');

      cy.get(`[data-cy="freeText22"]`).type('Borders Unbranded').should('have.value', 'Borders Unbranded');

      cy.get(`[data-cy="freeText23"]`).type('Configurable').should('have.value', 'Configurable');

      cy.get(`[data-cy="freeText24"]`).type('generating withdrawal Mali').should('have.value', 'generating withdrawal Mali');

      cy.get(`[data-cy="freeText25"]`).type('orchestrate Pakistan Cheese').should('have.value', 'orchestrate Pakistan Cheese');

      cy.get(`[data-cy="freeText26"]`).type('calculating overriding').should('have.value', 'calculating overriding');

      cy.get(`[data-cy="freeText27"]`).type('Health').should('have.value', 'Health');

      cy.get(`[data-cy="freeText28"]`).type('Soap Books withdrawal').should('have.value', 'Soap Books withdrawal');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T21:15').blur().should('have.value', '2023-07-25T21:15');

      cy.get(`[data-cy="createdBy"]`).type('Bike streamline Intranet').should('have.value', 'Bike streamline Intranet');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T10:45').blur().should('have.value', '2023-07-25T10:45');

      cy.get(`[data-cy="updatedBy"]`).type('Fresh Shirt').should('have.value', 'Fresh Shirt');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        customAudit = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', customAuditPageUrlPattern);
    });
  });
});
