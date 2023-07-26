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

describe('Branch e2e test', () => {
  const branchPageUrl = '/branch';
  const branchPageUrlPattern = new RegExp('/branch(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const branchSample = { branchName: 'copying Orchestrator', branchSwiftCode: 'Gorgeous Loaf', createdAt: '2023-07-25T18:38:14.733Z' };

  let branch;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/branches+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/branches').as('postEntityRequest');
    cy.intercept('DELETE', '/api/branches/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (branch) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/branches/${branch.id}`,
      }).then(() => {
        branch = undefined;
      });
    }
  });

  it('Branches menu should load Branches page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('branch');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Branch').should('exist');
    cy.url().should('match', branchPageUrlPattern);
  });

  describe('Branch page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(branchPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Branch page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/branch/new$'));
        cy.getEntityCreateUpdateHeading('Branch');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', branchPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/branches',
          body: branchSample,
        }).then(({ body }) => {
          branch = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/branches+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [branch],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(branchPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Branch page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('branch');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', branchPageUrlPattern);
      });

      it('edit button click should load edit Branch page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Branch');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', branchPageUrlPattern);
      });

      it('edit button click should load edit Branch page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Branch');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', branchPageUrlPattern);
      });

      it('last delete button click should delete instance of Branch', () => {
        cy.intercept('GET', '/api/branches/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('branch').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', branchPageUrlPattern);

        branch = undefined;
      });
    });
  });

  describe('new Branch page', () => {
    beforeEach(() => {
      cy.visit(`${branchPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Branch');
    });

    it('should create an instance of Branch', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('b76b7a46-d734-494d-94c2-18c07fdfcfe6')
        .invoke('val')
        .should('match', new RegExp('b76b7a46-d734-494d-94c2-18c07fdfcfe6'));

      cy.get(`[data-cy="recordId"]`).type('Borders').should('have.value', 'Borders');

      cy.get(`[data-cy="addressId"]`).type('turquoise').should('have.value', 'turquoise');

      cy.get(`[data-cy="bankId"]`).type('turquoise').should('have.value', 'turquoise');

      cy.get(`[data-cy="branchId"]`).type('Soft').should('have.value', 'Soft');

      cy.get(`[data-cy="branchName"]`).type('monitor Granite Home').should('have.value', 'monitor Granite Home');

      cy.get(`[data-cy="branchSwiftCode"]`).type('Inlet Internal Cross-group').should('have.value', 'Inlet Internal Cross-group');

      cy.get(`[data-cy="branchPhoneNumber"]`).type('Salad Lao Buckinghamshire').should('have.value', 'Salad Lao Buckinghamshire');

      cy.get(`[data-cy="branchEmail"]`).type('Hill').should('have.value', 'Hill');

      cy.get(`[data-cy="branchFreeField1"]`).type('microchip Account Cotton').should('have.value', 'microchip Account Cotton');

      cy.get(`[data-cy="branchFreeField3"]`).type('Kentucky').should('have.value', 'Kentucky');

      cy.get(`[data-cy="branchFreeField4"]`).type('Guyana Dynamic').should('have.value', 'Guyana Dynamic');

      cy.get(`[data-cy="branchFreeField5"]`)
        .type('Incredible Enterprise-wide niches')
        .should('have.value', 'Incredible Enterprise-wide niches');

      cy.get(`[data-cy="branchFreeField6"]`).type('invoice').should('have.value', 'invoice');

      cy.get(`[data-cy="branchFreeField7"]`).type('visualize Plastic').should('have.value', 'visualize Plastic');

      cy.get(`[data-cy="branchFreeField8"]`).type('optical').should('have.value', 'optical');

      cy.get(`[data-cy="branchFreeField9"]`).type('Avon Integration').should('have.value', 'Avon Integration');

      cy.get(`[data-cy="branchFreeField10"]`).type('empowering').should('have.value', 'empowering');

      cy.get(`[data-cy="branchFreeField11"]`).type('Loan deposit').should('have.value', 'Loan deposit');

      cy.get(`[data-cy="branchFreeField12"]`)
        .type('cross-platform California purple')
        .should('have.value', 'cross-platform California purple');

      cy.get(`[data-cy="branchFreeField13"]`).type('Dale').should('have.value', 'Dale');

      cy.get(`[data-cy="branchFreeField14"]`).type('hierarchy Fort').should('have.value', 'hierarchy Fort');

      cy.get(`[data-cy="branchFreeField15"]`).type('Computer Minnesota').should('have.value', 'Computer Minnesota');

      cy.get(`[data-cy="branchFreeField16"]`).type('Sleek').should('have.value', 'Sleek');

      cy.get(`[data-cy="branchFreeField17"]`).type('deposit Handcrafted visionary').should('have.value', 'deposit Handcrafted visionary');

      cy.get(`[data-cy="branchFreeField18"]`).type('neutral').should('have.value', 'neutral');

      cy.get(`[data-cy="branchFreeField19"]`).type('firewall Berkshire').should('have.value', 'firewall Berkshire');

      cy.get(`[data-cy="branchFreeField20"]`).type('deliverables blue Realigned').should('have.value', 'deliverables blue Realigned');

      cy.get(`[data-cy="branchFreeField21"]`).type('Producer Orchestrator Home').should('have.value', 'Producer Orchestrator Home');

      cy.get(`[data-cy="branchFreeField22"]`).type('Tactics Garden').should('have.value', 'Tactics Garden');

      cy.get(`[data-cy="branchFreeField23"]`).type('Loan Denmark').should('have.value', 'Loan Denmark');

      cy.get(`[data-cy="branchFreeField24"]`).type('markets').should('have.value', 'markets');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T19:44').blur().should('have.value', '2023-07-25T19:44');

      cy.get(`[data-cy="createdBy"]`).type('Grass-roots Investment').should('have.value', 'Grass-roots Investment');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T09:03').blur().should('have.value', '2023-07-25T09:03');

      cy.get(`[data-cy="updatedBy"]`).type('Cotton Buckinghamshire').should('have.value', 'Cotton Buckinghamshire');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-24T22:13').blur().should('have.value', '2023-07-24T22:13');

      cy.get(`[data-cy="status"]`).select('APPROVED');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        branch = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', branchPageUrlPattern);
    });
  });
});
