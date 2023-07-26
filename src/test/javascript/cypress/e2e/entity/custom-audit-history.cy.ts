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

describe('CustomAuditHistory e2e test', () => {
  const customAuditHistoryPageUrl = '/custom-audit-history';
  const customAuditHistoryPageUrlPattern = new RegExp('/custom-audit-history(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const customAuditHistorySample = { actionId: 'Y', createdAt: '2023-07-25T02:50:28.834Z' };

  let customAuditHistory;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/custom-audit-histories+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/custom-audit-histories').as('postEntityRequest');
    cy.intercept('DELETE', '/api/custom-audit-histories/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (customAuditHistory) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/custom-audit-histories/${customAuditHistory.id}`,
      }).then(() => {
        customAuditHistory = undefined;
      });
    }
  });

  it('CustomAuditHistories menu should load CustomAuditHistories page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('custom-audit-history');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('CustomAuditHistory').should('exist');
    cy.url().should('match', customAuditHistoryPageUrlPattern);
  });

  describe('CustomAuditHistory page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(customAuditHistoryPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create CustomAuditHistory page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/custom-audit-history/new$'));
        cy.getEntityCreateUpdateHeading('CustomAuditHistory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditHistoryPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/custom-audit-histories',
          body: customAuditHistorySample,
        }).then(({ body }) => {
          customAuditHistory = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/custom-audit-histories+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [customAuditHistory],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(customAuditHistoryPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details CustomAuditHistory page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('customAuditHistory');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditHistoryPageUrlPattern);
      });

      it('edit button click should load edit CustomAuditHistory page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CustomAuditHistory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditHistoryPageUrlPattern);
      });

      it('edit button click should load edit CustomAuditHistory page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('CustomAuditHistory');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditHistoryPageUrlPattern);
      });

      it('last delete button click should delete instance of CustomAuditHistory', () => {
        cy.intercept('GET', '/api/custom-audit-histories/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('customAuditHistory').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customAuditHistoryPageUrlPattern);

        customAuditHistory = undefined;
      });
    });
  });

  describe('new CustomAuditHistory page', () => {
    beforeEach(() => {
      cy.visit(`${customAuditHistoryPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('CustomAuditHistory');
    });

    it('should create an instance of CustomAuditHistory', () => {
      cy.get(`[data-cy="recordId"]`).type('Paradigm').should('have.value', 'Paradigm');

      cy.get(`[data-cy="actionId"]`).select('N');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-24T22:54').blur().should('have.value', '2023-07-24T22:54');

      cy.get(`[data-cy="oldValue"]`).type('Wyoming').should('have.value', 'Wyoming');

      cy.get(`[data-cy="newValue"]`).type('integrate').should('have.value', 'integrate');

      cy.get(`[data-cy="changeReason"]`).type('quantifying generating Iraqi').should('have.value', 'quantifying generating Iraqi');

      cy.get(`[data-cy="description"]`).type('International Senior synthesizing').should('have.value', 'International Senior synthesizing');

      cy.get(`[data-cy="description1"]`).type('target').should('have.value', 'target');

      cy.get(`[data-cy="description2"]`).type('Tools Florida').should('have.value', 'Tools Florida');

      cy.get(`[data-cy="description3"]`).type('payment').should('have.value', 'payment');

      cy.get(`[data-cy="description4"]`).type('Turnpike innovative').should('have.value', 'Turnpike innovative');

      cy.get(`[data-cy="description5"]`)
        .type('homogeneous Open-architected Health')
        .should('have.value', 'homogeneous Open-architected Health');

      cy.get(`[data-cy="description6"]`).type('Tools').should('have.value', 'Tools');

      cy.get(`[data-cy="description7"]`).type('Web').should('have.value', 'Web');

      cy.get(`[data-cy="description8"]`).type('logistical invoice Balanced').should('have.value', 'logistical invoice Balanced');

      cy.get(`[data-cy="description9"]`).type('Handmade').should('have.value', 'Handmade');

      cy.get(`[data-cy="freeText1"]`).type('deposit Account').should('have.value', 'deposit Account');

      cy.get(`[data-cy="freeText2"]`).type('cross-platform solid Shirt').should('have.value', 'cross-platform solid Shirt');

      cy.get(`[data-cy="freeText3"]`).type('interface').should('have.value', 'interface');

      cy.get(`[data-cy="freeText4"]`).type('strategic Concrete').should('have.value', 'strategic Concrete');

      cy.get(`[data-cy="freeText5"]`).type('Steel Handcrafted National').should('have.value', 'Steel Handcrafted National');

      cy.get(`[data-cy="freeText6"]`).type('incremental backing Market').should('have.value', 'incremental backing Market');

      cy.get(`[data-cy="freeText7"]`).type('Frozen transmitting program').should('have.value', 'Frozen transmitting program');

      cy.get(`[data-cy="freeText8"]`).type('multimedia').should('have.value', 'multimedia');

      cy.get(`[data-cy="freeText9"]`).type('Ergonomic').should('have.value', 'Ergonomic');

      cy.get(`[data-cy="freeText10"]`).type('upward-trending payment Dakota').should('have.value', 'upward-trending payment Dakota');

      cy.get(`[data-cy="freeText11"]`).type('Gibraltar').should('have.value', 'Gibraltar');

      cy.get(`[data-cy="freeText12"]`).type('services Savings').should('have.value', 'services Savings');

      cy.get(`[data-cy="freeText13"]`).type('Industrial Books').should('have.value', 'Industrial Books');

      cy.get(`[data-cy="freeText14"]`).type('Engineer Licensed').should('have.value', 'Engineer Licensed');

      cy.get(`[data-cy="freeText15"]`).type('Gloves').should('have.value', 'Gloves');

      cy.get(`[data-cy="freeText16"]`).type('Lempira Soap').should('have.value', 'Lempira Soap');

      cy.get(`[data-cy="freeText17"]`).type('Accounts program').should('have.value', 'Accounts program');

      cy.get(`[data-cy="freeText18"]`).type('Car THX').should('have.value', 'Car THX');

      cy.get(`[data-cy="freeText19"]`).type('microchip virtual').should('have.value', 'microchip virtual');

      cy.get(`[data-cy="freeText20"]`).type('Baby withdrawal Baht').should('have.value', 'Baby withdrawal Baht');

      cy.get(`[data-cy="freeText21"]`).type('blue').should('have.value', 'blue');

      cy.get(`[data-cy="freeText22"]`).type('extensible Inlet').should('have.value', 'extensible Inlet');

      cy.get(`[data-cy="freeText23"]`).type('withdrawal Keyboard firewall').should('have.value', 'withdrawal Keyboard firewall');

      cy.get(`[data-cy="freeText24"]`).type('turquoise').should('have.value', 'turquoise');

      cy.get(`[data-cy="freeText25"]`).type('Investor').should('have.value', 'Investor');

      cy.get(`[data-cy="freeText26"]`).type('Specialist Costa').should('have.value', 'Specialist Costa');

      cy.get(`[data-cy="freeText27"]`).type('Metal JBOD').should('have.value', 'Metal JBOD');

      cy.get(`[data-cy="freeText28"]`).type('Jewelery Handcrafted').should('have.value', 'Jewelery Handcrafted');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T14:23').blur().should('have.value', '2023-07-25T14:23');

      cy.get(`[data-cy="createdBy"]`).type('pixel GB ivory').should('have.value', 'pixel GB ivory');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T07:42').blur().should('have.value', '2023-07-25T07:42');

      cy.get(`[data-cy="updatedBy"]`).type('Practical Cambridgeshire').should('have.value', 'Practical Cambridgeshire');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        customAuditHistory = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', customAuditHistoryPageUrlPattern);
    });
  });
});
