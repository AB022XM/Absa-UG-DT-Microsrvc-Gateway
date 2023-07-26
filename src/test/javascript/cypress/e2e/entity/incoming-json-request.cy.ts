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

describe('IncomingJSONRequest e2e test', () => {
  const incomingJSONRequestPageUrl = '/incoming-json-request';
  const incomingJSONRequestPageUrlPattern = new RegExp('/incoming-json-request(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const incomingJSONRequestSample = {
    requestJson: 'Li4vZmFrZS1kYXRhL2Jsb2IvaGlwc3Rlci50eHQ=',
    requestType: 'Market',
    timestamp: '2023-07-24T22:20:07.802Z',
  };

  let incomingJSONRequest;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/incoming-json-requests+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/incoming-json-requests').as('postEntityRequest');
    cy.intercept('DELETE', '/api/incoming-json-requests/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (incomingJSONRequest) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/incoming-json-requests/${incomingJSONRequest.id}`,
      }).then(() => {
        incomingJSONRequest = undefined;
      });
    }
  });

  it('IncomingJSONRequests menu should load IncomingJSONRequests page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('incoming-json-request');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('IncomingJSONRequest').should('exist');
    cy.url().should('match', incomingJSONRequestPageUrlPattern);
  });

  describe('IncomingJSONRequest page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(incomingJSONRequestPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create IncomingJSONRequest page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/incoming-json-request/new$'));
        cy.getEntityCreateUpdateHeading('IncomingJSONRequest');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONRequestPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/incoming-json-requests',
          body: incomingJSONRequestSample,
        }).then(({ body }) => {
          incomingJSONRequest = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/incoming-json-requests+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [incomingJSONRequest],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(incomingJSONRequestPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details IncomingJSONRequest page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('incomingJSONRequest');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONRequestPageUrlPattern);
      });

      it('edit button click should load edit IncomingJSONRequest page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('IncomingJSONRequest');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONRequestPageUrlPattern);
      });

      it('edit button click should load edit IncomingJSONRequest page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('IncomingJSONRequest');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONRequestPageUrlPattern);
      });

      it('last delete button click should delete instance of IncomingJSONRequest', () => {
        cy.intercept('GET', '/api/incoming-json-requests/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('incomingJSONRequest').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', incomingJSONRequestPageUrlPattern);

        incomingJSONRequest = undefined;
      });
    });
  });

  describe('new IncomingJSONRequest page', () => {
    beforeEach(() => {
      cy.visit(`${incomingJSONRequestPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('IncomingJSONRequest');
    });

    it('should create an instance of IncomingJSONRequest', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('cc877add-5e66-4fb2-9e1e-6bd12ead3efa')
        .invoke('val')
        .should('match', new RegExp('cc877add-5e66-4fb2-9e1e-6bd12ead3efa'));

      cy.get(`[data-cy="dtDTransactionId"]`).type('Total protocol Cambridgeshire').should('have.value', 'Total protocol Cambridgeshire');

      cy.get(`[data-cy="amolDTransactionId"]`).type('bypassing').should('have.value', 'bypassing');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('deliverables bandwidth').should('have.value', 'deliverables bandwidth');

      cy.get(`[data-cy="token"]`).type('Credit').should('have.value', 'Credit');

      cy.get(`[data-cy="transactionId"]`).type('blue repurpose Health').should('have.value', 'blue repurpose Health');

      cy.get(`[data-cy="fromEndpoint"]`).type('circuit invoice turn-key').should('have.value', 'circuit invoice turn-key');

      cy.get(`[data-cy="toEndpoint"]`).type('Jewelery program Automotive').should('have.value', 'Jewelery program Automotive');

      cy.get(`[data-cy="requestJson"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="requestType"]`).type('Slovenia Connecticut Programmable').should('have.value', 'Slovenia Connecticut Programmable');

      cy.get(`[data-cy="requestDescription"]`).type('ivory').should('have.value', 'ivory');

      cy.get(`[data-cy="requestStatus"]`).type('metrics Bedfordshire generate').should('have.value', 'metrics Bedfordshire generate');

      cy.get(`[data-cy="additionalInfo"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField1"]`).type('productivity Table Wooden').should('have.value', 'productivity Table Wooden');

      cy.get(`[data-cy="freeField2"]`).type('optical').should('have.value', 'optical');

      cy.get(`[data-cy="freeField3"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField4"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField5"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField6', 'integration-test.png', 'image/png');

      cy.setFieldImageAsBytesOfEntity('freeField7', 'integration-test.png', 'image/png');

      cy.setFieldImageAsBytesOfEntity('freeField8', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField9"]`).type('payment monitor').should('have.value', 'payment monitor');

      cy.get(`[data-cy="freeField10"]`).should('not.be.checked');
      cy.get(`[data-cy="freeField10"]`).click().should('be.checked');

      cy.get(`[data-cy="freeField11"]`).should('not.be.checked');
      cy.get(`[data-cy="freeField11"]`).click().should('be.checked');

      cy.get(`[data-cy="freeField12"]`).type('6867').should('have.value', '6867');

      cy.get(`[data-cy="freeField13"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField14"]`).type('wireless').should('have.value', 'wireless');

      cy.get(`[data-cy="freeField15"]`).type('Officer Rest').should('have.value', 'Officer Rest');

      cy.get(`[data-cy="freeField16"]`).type('parse').should('have.value', 'parse');

      cy.get(`[data-cy="freeField17"]`).type('Dollar frictionless').should('have.value', 'Dollar frictionless');

      cy.get(`[data-cy="freeField18"]`).type('approach interactive').should('have.value', 'approach interactive');

      cy.get(`[data-cy="freeField19"]`).type('International platforms').should('have.value', 'International platforms');

      cy.get(`[data-cy="freeField20"]`).type('Frozen sky Executive').should('have.value', 'Frozen sky Executive');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T00:29').blur().should('have.value', '2023-07-25T00:29');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T02:42').blur().should('have.value', '2023-07-25T02:42');

      cy.get(`[data-cy="createdBy"]`).type('Security').should('have.value', 'Security');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T19:44').blur().should('have.value', '2023-07-25T19:44');

      cy.get(`[data-cy="updatedBy"]`).type('distributed Developer').should('have.value', 'distributed Developer');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        incomingJSONRequest = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', incomingJSONRequestPageUrlPattern);
    });
  });
});
