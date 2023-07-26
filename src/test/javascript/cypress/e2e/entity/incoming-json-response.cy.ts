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

describe('IncomingJSONResponse e2e test', () => {
  const incomingJSONResponsePageUrl = '/incoming-json-response';
  const incomingJSONResponsePageUrlPattern = new RegExp('/incoming-json-response(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const incomingJSONResponseSample = {
    responseJson: 'Li4vZmFrZS1kYXRhL2Jsb2IvaGlwc3Rlci50eHQ=',
    responseType: 'Tasty Planner Assistant',
    timestamp: '2023-07-24T22:37:45.018Z',
  };

  let incomingJSONResponse;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/incoming-json-responses+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/incoming-json-responses').as('postEntityRequest');
    cy.intercept('DELETE', '/api/incoming-json-responses/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (incomingJSONResponse) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/incoming-json-responses/${incomingJSONResponse.id}`,
      }).then(() => {
        incomingJSONResponse = undefined;
      });
    }
  });

  it('IncomingJSONResponses menu should load IncomingJSONResponses page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('incoming-json-response');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('IncomingJSONResponse').should('exist');
    cy.url().should('match', incomingJSONResponsePageUrlPattern);
  });

  describe('IncomingJSONResponse page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(incomingJSONResponsePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create IncomingJSONResponse page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/incoming-json-response/new$'));
        cy.getEntityCreateUpdateHeading('IncomingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONResponsePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/incoming-json-responses',
          body: incomingJSONResponseSample,
        }).then(({ body }) => {
          incomingJSONResponse = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/incoming-json-responses+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [incomingJSONResponse],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(incomingJSONResponsePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details IncomingJSONResponse page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('incomingJSONResponse');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONResponsePageUrlPattern);
      });

      it('edit button click should load edit IncomingJSONResponse page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('IncomingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONResponsePageUrlPattern);
      });

      it('edit button click should load edit IncomingJSONResponse page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('IncomingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONResponsePageUrlPattern);
      });

      it('last delete button click should delete instance of IncomingJSONResponse', () => {
        cy.intercept('GET', '/api/incoming-json-responses/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('incomingJSONResponse').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONResponsePageUrlPattern);

        incomingJSONResponse = undefined;
      });
    });
  });

  describe('new IncomingJSONResponse page', () => {
    beforeEach(() => {
      cy.visit(`${incomingJSONResponsePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('IncomingJSONResponse');
    });

    it('should create an instance of IncomingJSONResponse', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('7cfddbfb-70de-43b4-a57d-8a5641a8404b')
        .invoke('val')
        .should('match', new RegExp('7cfddbfb-70de-43b4-a57d-8a5641a8404b'));

      cy.get(`[data-cy="dtDTransactionId"]`).type('demand-driven Industrial').should('have.value', 'demand-driven Industrial');

      cy.get(`[data-cy="amolDTransactionId"]`).type('circuit Brand').should('have.value', 'circuit Brand');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('channels Loan Steel').should('have.value', 'channels Loan Steel');

      cy.get(`[data-cy="token"]`).type('Cambridgeshire HDD Nebraska').should('have.value', 'Cambridgeshire HDD Nebraska');

      cy.get(`[data-cy="responseId"]`).type('Car Berkshire').should('have.value', 'Car Berkshire');

      cy.get(`[data-cy="transactionId"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="responseJson"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="responseType"]`).type('Analyst').should('have.value', 'Analyst');

      cy.get(`[data-cy="responseDescription"]`).type('lime SQL').should('have.value', 'lime SQL');

      cy.get(`[data-cy="responseStatus"]`).type('disintermediate').should('have.value', 'disintermediate');

      cy.get(`[data-cy="additionalInfo"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T07:21').blur().should('have.value', '2023-07-25T07:21');

      cy.get(`[data-cy="exceptionMessage"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField"]`).type('Home Chair leverage').should('have.value', 'Home Chair leverage');

      cy.get(`[data-cy="freeField1"]`).type('reboot').should('have.value', 'reboot');

      cy.get(`[data-cy="freeField2"]`).type('Universal').should('have.value', 'Universal');

      cy.get(`[data-cy="freeField3"]`).type('strategic Car Fish').should('have.value', 'strategic Car Fish');

      cy.get(`[data-cy="freeField4"]`).type('South Intranet').should('have.value', 'South Intranet');

      cy.get(`[data-cy="freeField5"]`).type('Infrastructure').should('have.value', 'Infrastructure');

      cy.get(`[data-cy="freeField6"]`).type('vertical Australia Liaison').should('have.value', 'vertical Australia Liaison');

      cy.get(`[data-cy="freeField8"]`).type('actuating').should('have.value', 'actuating');

      cy.get(`[data-cy="freeField9"]`).type('turquoise Sleek Investor').should('have.value', 'turquoise Sleek Investor');

      cy.get(`[data-cy="freeField10"]`).type('XML').should('have.value', 'XML');

      cy.get(`[data-cy="freeField11"]`).type('Buckinghamshire magenta Dirham').should('have.value', 'Buckinghamshire magenta Dirham');

      cy.get(`[data-cy="freeField12"]`).type('West').should('have.value', 'West');

      cy.get(`[data-cy="freeField13"]`).type('Frozen Key').should('have.value', 'Frozen Key');

      cy.get(`[data-cy="freeField14"]`).type('panel Jersey Chair').should('have.value', 'panel Jersey Chair');

      cy.setFieldImageAsBytesOfEntity('freeField15', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField16"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField17"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField18', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField19"]`).type('maroon Bike').should('have.value', 'maroon Bike');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T12:16').blur().should('have.value', '2023-07-25T12:16');

      cy.get(`[data-cy="createdBy"]`).type('encoding').should('have.value', 'encoding');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T04:39').blur().should('have.value', '2023-07-25T04:39');

      cy.get(`[data-cy="updatedBy"]`).type('Granite Ergonomic').should('have.value', 'Granite Ergonomic');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        incomingJSONResponse = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', incomingJSONResponsePageUrlPattern);
    });
  });
});
