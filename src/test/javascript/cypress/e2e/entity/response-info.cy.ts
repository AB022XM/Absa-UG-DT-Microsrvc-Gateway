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

describe('ResponseInfo e2e test', () => {
  const responseInfoPageUrl = '/response-info';
  const responseInfoPageUrlPattern = new RegExp('/response-info(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const responseInfoSample = { billerId: 'Avon parsing', paymentItemCode: 'whiteboard Handcrafted' };

  let responseInfo;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/response-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/response-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/response-infos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (responseInfo) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/response-infos/${responseInfo.id}`,
      }).then(() => {
        responseInfo = undefined;
      });
    }
  });

  it('ResponseInfos menu should load ResponseInfos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('response-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('ResponseInfo').should('exist');
    cy.url().should('match', responseInfoPageUrlPattern);
  });

  describe('ResponseInfo page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(responseInfoPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create ResponseInfo page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/response-info/new$'));
        cy.getEntityCreateUpdateHeading('ResponseInfo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', responseInfoPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/response-infos',
          body: responseInfoSample,
        }).then(({ body }) => {
          responseInfo = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/response-infos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [responseInfo],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(responseInfoPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details ResponseInfo page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('responseInfo');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', responseInfoPageUrlPattern);
      });

      it('edit button click should load edit ResponseInfo page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ResponseInfo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', responseInfoPageUrlPattern);
      });

      it('edit button click should load edit ResponseInfo page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ResponseInfo');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', responseInfoPageUrlPattern);
      });

      it('last delete button click should delete instance of ResponseInfo', () => {
        cy.intercept('GET', '/api/response-infos/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('responseInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', responseInfoPageUrlPattern);

        responseInfo = undefined;
      });
    });
  });

  describe('new ResponseInfo page', () => {
    beforeEach(() => {
      cy.visit(`${responseInfoPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('ResponseInfo');
    });

    it('should create an instance of ResponseInfo', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('424a59cb-c7a3-4b8f-a6da-c77e950c6ead')
        .invoke('val')
        .should('match', new RegExp('424a59cb-c7a3-4b8f-a6da-c77e950c6ead'));

      cy.get(`[data-cy="billerId"]`).type('Factors').should('have.value', 'Factors');

      cy.get(`[data-cy="paymentItemCode"]`).type('protocol array').should('have.value', 'protocol array');

      cy.get(`[data-cy="dtDTransactionId"]`).type('scalable functionalities').should('have.value', 'scalable functionalities');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('SDD').should('have.value', 'SDD');

      cy.get(`[data-cy="externalTranid"]`).type('Architect Montana').should('have.value', 'Architect Montana');

      cy.get(`[data-cy="responseCode"]`).type('Kenyan Dinar').should('have.value', 'Kenyan Dinar');

      cy.get(`[data-cy="responseMessage"]`).type('payment').should('have.value', 'payment');

      cy.get(`[data-cy="responseBody"]`).type('Oval 1080p').should('have.value', 'Oval 1080p');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T07:04').blur().should('have.value', '2023-07-25T07:04');

      cy.get(`[data-cy="requestRef"]`).type('integrated').should('have.value', 'integrated');

      cy.get(`[data-cy="status"]`).select('FAILED');

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField2', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField3"]`).type('Rustic').should('have.value', 'Rustic');

      cy.get(`[data-cy="freeField4"]`).type('enhance').should('have.value', 'enhance');

      cy.get(`[data-cy="freeField5"]`).type('input').should('have.value', 'input');

      cy.get(`[data-cy="freeField6"]`).type('Illinois Sleek').should('have.value', 'Illinois Sleek');

      cy.get(`[data-cy="time"]`).type('2023-07-25T04:01').blur().should('have.value', '2023-07-25T04:01');

      cy.get(`[data-cy="errorCode"]`).select('INVALIDBILLERCODE');

      cy.get(`[data-cy="errorMessage"]`).select('TRANSACTIONAPPROVED');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-24T23:44').blur().should('have.value', '2023-07-24T23:44');

      cy.get(`[data-cy="createdBy"]`).type('Well middleware Persistent').should('have.value', 'Well middleware Persistent');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T15:46').blur().should('have.value', '2023-07-25T15:46');

      cy.get(`[data-cy="updatedBy"]`).type('Fantastic reboot').should('have.value', 'Fantastic reboot');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        responseInfo = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', responseInfoPageUrlPattern);
    });
  });
});
