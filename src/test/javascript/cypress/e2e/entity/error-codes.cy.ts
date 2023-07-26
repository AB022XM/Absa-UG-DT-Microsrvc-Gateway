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

describe('ErrorCodes e2e test', () => {
  const errorCodesPageUrl = '/error-codes';
  const errorCodesPageUrlPattern = new RegExp('/error-codes(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const errorCodesSample = {
    paymentItemCode: 'Delaware maroon Generic',
    errorCode: 'Guinea Movies',
    createdAt: '2023-07-25T18:31:41.897Z',
  };

  let errorCodes;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/error-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/error-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/error-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (errorCodes) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/error-codes/${errorCodes.id}`,
      }).then(() => {
        errorCodes = undefined;
      });
    }
  });

  it('ErrorCodes menu should load ErrorCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('error-codes');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('ErrorCodes').should('exist');
    cy.url().should('match', errorCodesPageUrlPattern);
  });

  describe('ErrorCodes page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(errorCodesPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create ErrorCodes page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/error-codes/new$'));
        cy.getEntityCreateUpdateHeading('ErrorCodes');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', errorCodesPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/error-codes',
          body: errorCodesSample,
        }).then(({ body }) => {
          errorCodes = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/error-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [errorCodes],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(errorCodesPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details ErrorCodes page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('errorCodes');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', errorCodesPageUrlPattern);
      });

      it('edit button click should load edit ErrorCodes page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ErrorCodes');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', errorCodesPageUrlPattern);
      });

      it('edit button click should load edit ErrorCodes page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ErrorCodes');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', errorCodesPageUrlPattern);
      });

      it('last delete button click should delete instance of ErrorCodes', () => {
        cy.intercept('GET', '/api/error-codes/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('errorCodes').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', errorCodesPageUrlPattern);

        errorCodes = undefined;
      });
    });
  });

  describe('new ErrorCodes page', () => {
    beforeEach(() => {
      cy.visit(`${errorCodesPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('ErrorCodes');
    });

    it('should create an instance of ErrorCodes', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('4bd7fa34-1405-41b3-a478-0bf9401d41df')
        .invoke('val')
        .should('match', new RegExp('4bd7fa34-1405-41b3-a478-0bf9401d41df'));

      cy.get(`[data-cy="recordId"]`).type('deploy').should('have.value', 'deploy');

      cy.get(`[data-cy="paymentItemCode"]`).type('base Chicken').should('have.value', 'base Chicken');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Director').should('have.value', 'Director');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Kids').should('have.value', 'Kids');

      cy.get(`[data-cy="externalTranid"]`).type('hub open-source microchip').should('have.value', 'hub open-source microchip');

      cy.get(`[data-cy="errorCode"]`).type('evolve Licensed complexity').should('have.value', 'evolve Licensed complexity');

      cy.get(`[data-cy="errorMessage"]`).select('TRANSACTIONUNKNOWNERROR');

      cy.get(`[data-cy="responseCode"]`).type('AI Dynamic Cheese').should('have.value', 'AI Dynamic Cheese');

      cy.get(`[data-cy="responseMessage"]`).type('Israeli').should('have.value', 'Israeli');

      cy.get(`[data-cy="responseBody"]`).type('6th hard').should('have.value', '6th hard');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T19:53').blur().should('have.value', '2023-07-25T19:53');

      cy.get(`[data-cy="requestRef"]`).type('human-resource task-force').should('have.value', 'human-resource task-force');

      cy.get(`[data-cy="status"]`).select('SUCCESS');

      cy.get(`[data-cy="customerField1"]`).type('Rubber').should('have.value', 'Rubber');

      cy.get(`[data-cy="customerField2"]`).type('Virginia Fish Salad').should('have.value', 'Virginia Fish Salad');

      cy.get(`[data-cy="customerField3"]`).type('Fish').should('have.value', 'Fish');

      cy.get(`[data-cy="customerField4"]`).type('Assistant Gorgeous').should('have.value', 'Assistant Gorgeous');

      cy.get(`[data-cy="customerField5"]`).type('payment').should('have.value', 'payment');

      cy.get(`[data-cy="customerField6"]`).type('hack Afghanistan Taka').should('have.value', 'hack Afghanistan Taka');

      cy.get(`[data-cy="customerField7"]`).type('Account Account').should('have.value', 'Account Account');

      cy.get(`[data-cy="customerField8"]`).type('Buckinghamshire').should('have.value', 'Buckinghamshire');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T18:41').blur().should('have.value', '2023-07-25T18:41');

      cy.get(`[data-cy="createdBy"]`).type('Cambridgeshire forecast Markets').should('have.value', 'Cambridgeshire forecast Markets');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T08:45').blur().should('have.value', '2023-07-25T08:45');

      cy.get(`[data-cy="updatedBy"]`).type('Louisiana capability').should('have.value', 'Louisiana capability');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        errorCodes = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', errorCodesPageUrlPattern);
    });
  });
});
