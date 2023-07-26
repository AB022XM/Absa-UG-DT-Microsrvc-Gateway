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

describe('RequestInfo e2e test', () => {
  const requestInfoPageUrl = '/request-info';
  const requestInfoPageUrlPattern = new RegExp('/request-info(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const requestInfoSample = { createdAt: '2023-07-25T10:27:59.979Z' };

  let requestInfo;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/request-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/request-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/request-infos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (requestInfo) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/request-infos/${requestInfo.id}`,
      }).then(() => {
        requestInfo = undefined;
      });
    }
  });

  it('RequestInfos menu should load RequestInfos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('request-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('RequestInfo').should('exist');
    cy.url().should('match', requestInfoPageUrlPattern);
  });

  describe('RequestInfo page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(requestInfoPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create RequestInfo page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/request-info/new$'));
        cy.getEntityCreateUpdateHeading('RequestInfo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', requestInfoPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/request-infos',
          body: requestInfoSample,
        }).then(({ body }) => {
          requestInfo = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/request-infos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [requestInfo],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(requestInfoPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details RequestInfo page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('requestInfo');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', requestInfoPageUrlPattern);
      });

      it('edit button click should load edit RequestInfo page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('RequestInfo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', requestInfoPageUrlPattern);
      });

      it('edit button click should load edit RequestInfo page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('RequestInfo');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', requestInfoPageUrlPattern);
      });

      it('last delete button click should delete instance of RequestInfo', () => {
        cy.intercept('GET', '/api/request-infos/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('requestInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', requestInfoPageUrlPattern);

        requestInfo = undefined;
      });
    });
  });

  describe('new RequestInfo page', () => {
    beforeEach(() => {
      cy.visit(`${requestInfoPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('RequestInfo');
    });

    it('should create an instance of RequestInfo', () => {
      cy.get(`[data-cy="recordId"]`).type('algorithm olive New').should('have.value', 'algorithm olive New');

      cy.get(`[data-cy="transactionId"]`).type('fuchsia').should('have.value', 'fuchsia');

      cy.get(`[data-cy="requestData"]`).type('Soft Incredible').should('have.value', 'Soft Incredible');

      cy.get(`[data-cy="paramList"]`).type('Point').should('have.value', 'Point');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T09:12').blur().should('have.value', '2023-07-25T09:12');

      cy.get(`[data-cy="requestRef"]`).type('override repurpose Practical').should('have.value', 'override repurpose Practical');

      cy.get(`[data-cy="status"]`).select('PENDING');

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField2"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField3"]`).type('maximized Supervisor').should('have.value', 'maximized Supervisor');

      cy.get(`[data-cy="freeField4"]`).type('FTP e-business').should('have.value', 'FTP e-business');

      cy.get(`[data-cy="freeField5"]`).type('monetize').should('have.value', 'monetize');

      cy.get(`[data-cy="freeField6"]`).type('Sausages help-desk white').should('have.value', 'Sausages help-desk white');

      cy.get(`[data-cy="time"]`).type('2023-07-25T13:47').blur().should('have.value', '2023-07-25T13:47');

      cy.get(`[data-cy="errorCode"]`).select('INVALIDPAYMENTMETHOD');

      cy.get(`[data-cy="errorMessage"]`).select('TRANSACTIONUNKNOWNERROR');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T08:30').blur().should('have.value', '2023-07-25T08:30');

      cy.get(`[data-cy="createdBy"]`).type('demand-driven Enhanced').should('have.value', 'demand-driven Enhanced');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T09:29').blur().should('have.value', '2023-07-25T09:29');

      cy.get(`[data-cy="updatedBy"]`).type('infomediaries indexing').should('have.value', 'infomediaries indexing');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        requestInfo = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', requestInfoPageUrlPattern);
    });
  });
});
