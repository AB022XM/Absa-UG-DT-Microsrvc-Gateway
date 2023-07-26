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

describe('OutgoingJSONResponse e2e test', () => {
  const outgoingJSONResponsePageUrl = '/outgoing-json-response';
  const outgoingJSONResponsePageUrlPattern = new RegExp('/outgoing-json-response(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const outgoingJSONResponseSample = {
    responseJson: 'Li4vZmFrZS1kYXRhL2Jsb2IvaGlwc3Rlci50eHQ=',
    responseType: 'Gorgeous',
    timestamp: '2023-07-25T04:13:48.287Z',
  };

  let outgoingJSONResponse;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/outgoing-json-responses+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/outgoing-json-responses').as('postEntityRequest');
    cy.intercept('DELETE', '/api/outgoing-json-responses/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (outgoingJSONResponse) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/outgoing-json-responses/${outgoingJSONResponse.id}`,
      }).then(() => {
        outgoingJSONResponse = undefined;
      });
    }
  });

  it('OutgoingJSONResponses menu should load OutgoingJSONResponses page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('outgoing-json-response');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('OutgoingJSONResponse').should('exist');
    cy.url().should('match', outgoingJSONResponsePageUrlPattern);
  });

  describe('OutgoingJSONResponse page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(outgoingJSONResponsePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create OutgoingJSONResponse page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/outgoing-json-response/new$'));
        cy.getEntityCreateUpdateHeading('OutgoingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', outgoingJSONResponsePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/outgoing-json-responses',
          body: outgoingJSONResponseSample,
        }).then(({ body }) => {
          outgoingJSONResponse = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/outgoing-json-responses+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [outgoingJSONResponse],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(outgoingJSONResponsePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details OutgoingJSONResponse page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('outgoingJSONResponse');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', outgoingJSONResponsePageUrlPattern);
      });

      it('edit button click should load edit OutgoingJSONResponse page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('OutgoingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', outgoingJSONResponsePageUrlPattern);
      });

      it('edit button click should load edit OutgoingJSONResponse page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('OutgoingJSONResponse');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', outgoingJSONResponsePageUrlPattern);
      });

      it('last delete button click should delete instance of OutgoingJSONResponse', () => {
        cy.intercept('GET', '/api/outgoing-json-responses/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('outgoingJSONResponse').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', outgoingJSONResponsePageUrlPattern);

        outgoingJSONResponse = undefined;
      });
    });
  });

  describe('new OutgoingJSONResponse page', () => {
    beforeEach(() => {
      cy.visit(`${outgoingJSONResponsePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('OutgoingJSONResponse');
    });

    it('should create an instance of OutgoingJSONResponse', () => {
      cy.get(`[data-cy="recordId"]`).type('Metal').should('have.value', 'Metal');

      cy.get(`[data-cy="responseId"]`).type('eyeballs e-business Islands').should('have.value', 'eyeballs e-business Islands');

      cy.get(`[data-cy="transactionId"]`).type('fuchsia invoice copy').should('have.value', 'fuchsia invoice copy');

      cy.get(`[data-cy="responseJson"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="responseType"]`).type('incentivize Handcrafted').should('have.value', 'incentivize Handcrafted');

      cy.get(`[data-cy="responseDescription"]`).type('copying eyeballs orange').should('have.value', 'copying eyeballs orange');

      cy.get(`[data-cy="toEndpoint"]`).type('deliverables Car Officer').should('have.value', 'deliverables Car Officer');

      cy.get(`[data-cy="fromEndpoint"]`).type('Networked Ergonomic').should('have.value', 'Networked Ergonomic');

      cy.get(`[data-cy="responseStatus"]`).type('cross-platform RAM').should('have.value', 'cross-platform RAM');

      cy.get(`[data-cy="additionalInfo"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T00:41').blur().should('have.value', '2023-07-25T00:41');

      cy.get(`[data-cy="exceptionMessage"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField"]`).type('panel Fiji').should('have.value', 'panel Fiji');

      cy.get(`[data-cy="freeField1"]`).type('Norway Clothing granular').should('have.value', 'Norway Clothing granular');

      cy.get(`[data-cy="freeField2"]`).type('1080p Security').should('have.value', '1080p Security');

      cy.get(`[data-cy="freeField3"]`).type('bandwidth-monitored').should('have.value', 'bandwidth-monitored');

      cy.get(`[data-cy="freeField4"]`).type('Industrial solutions wireless').should('have.value', 'Industrial solutions wireless');

      cy.get(`[data-cy="freeField5"]`).type('intranet').should('have.value', 'intranet');

      cy.get(`[data-cy="freeField6"]`).type('Dong').should('have.value', 'Dong');

      cy.get(`[data-cy="freeField8"]`).type('Concrete Cotton').should('have.value', 'Concrete Cotton');

      cy.get(`[data-cy="freeField9"]`).type('Gorgeous Programmable Parks').should('have.value', 'Gorgeous Programmable Parks');

      cy.get(`[data-cy="freeField10"]`).type('orchid innovative communities').should('have.value', 'orchid innovative communities');

      cy.get(`[data-cy="freeField11"]`).type('black').should('have.value', 'black');

      cy.get(`[data-cy="freeField12"]`).type('orange Yemen West').should('have.value', 'orange Yemen West');

      cy.get(`[data-cy="freeField13"]`).type('Route Alaska').should('have.value', 'Route Alaska');

      cy.get(`[data-cy="freeField14"]`).type('engineer').should('have.value', 'engineer');

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

      cy.get(`[data-cy="freeField19"]`).type('Berkshire alarm solutions').should('have.value', 'Berkshire alarm solutions');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T05:03').blur().should('have.value', '2023-07-25T05:03');

      cy.get(`[data-cy="createdBy"]`).type('International').should('have.value', 'International');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-24T21:54').blur().should('have.value', '2023-07-24T21:54');

      cy.get(`[data-cy="updatedBy"]`).type('Account index technologies').should('have.value', 'Account index technologies');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        outgoingJSONResponse = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', outgoingJSONResponsePageUrlPattern);
    });
  });
});
