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

describe('DTransactionSummary e2e test', () => {
  const dTransactionSummaryPageUrl = '/d-transaction-summary';
  const dTransactionSummaryPageUrlPattern = new RegExp('/d-transaction-summary(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dTransactionSummarySample = {
    billerId: 'optical',
    paymentItemCode: 'fuchsia withdrawal',
    transactionType: 'transmit Bulgaria',
    transactionAmount: 'Outdoors',
    createdAt: '2023-07-25T12:03:35.301Z',
  };

  let dTransactionSummary;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-transaction-summaries+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-transaction-summaries').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-transaction-summaries/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dTransactionSummary) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-transaction-summaries/${dTransactionSummary.id}`,
      }).then(() => {
        dTransactionSummary = undefined;
      });
    }
  });

  it('DTransactionSummaries menu should load DTransactionSummaries page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-transaction-summary');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DTransactionSummary').should('exist');
    cy.url().should('match', dTransactionSummaryPageUrlPattern);
  });

  describe('DTransactionSummary page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dTransactionSummaryPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DTransactionSummary page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-transaction-summary/new$'));
        cy.getEntityCreateUpdateHeading('DTransactionSummary');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionSummaryPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-transaction-summaries',
          body: dTransactionSummarySample,
        }).then(({ body }) => {
          dTransactionSummary = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-transaction-summaries+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [dTransactionSummary],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(dTransactionSummaryPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DTransactionSummary page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dTransactionSummary');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionSummaryPageUrlPattern);
      });

      it('edit button click should load edit DTransactionSummary page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionSummary');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionSummaryPageUrlPattern);
      });

      it('edit button click should load edit DTransactionSummary page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionSummary');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionSummaryPageUrlPattern);
      });

      it('last delete button click should delete instance of DTransactionSummary', () => {
        cy.intercept('GET', '/api/d-transaction-summaries/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dTransactionSummary').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionSummaryPageUrlPattern);

        dTransactionSummary = undefined;
      });
    });
  });

  describe('new DTransactionSummary page', () => {
    beforeEach(() => {
      cy.visit(`${dTransactionSummaryPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DTransactionSummary');
    });

    it('should create an instance of DTransactionSummary', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('f834ebdf-45f5-4b09-9955-f1a8f3e8de12')
        .invoke('val')
        .should('match', new RegExp('f834ebdf-45f5-4b09-9955-f1a8f3e8de12'));

      cy.get(`[data-cy="billerId"]`).type('invoice Underpass').should('have.value', 'invoice Underpass');

      cy.get(`[data-cy="paymentItemCode"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="dtDTransactionId"]`).type('transparent navigate').should('have.value', 'transparent navigate');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Home').should('have.value', 'Home');

      cy.get(`[data-cy="recordId"]`).type('auxiliary parallelism').should('have.value', 'auxiliary parallelism');

      cy.get(`[data-cy="transactionId"]`).type('Ergonomic').should('have.value', 'Ergonomic');

      cy.get(`[data-cy="transactionType"]`).type('Pizza calculate disintermediate').should('have.value', 'Pizza calculate disintermediate');

      cy.get(`[data-cy="transactionAmount"]`).type('architect').should('have.value', 'architect');

      cy.get(`[data-cy="transactionDate"]`).type('2023-07-25T19:10').blur().should('have.value', '2023-07-25T19:10');

      cy.get(`[data-cy="transactionStatus"]`).select('PENDING');

      cy.get(`[data-cy="freeField1"]`).type('synthesize').should('have.value', 'synthesize');

      cy.get(`[data-cy="freeField2"]`).type('Licensed magnetic Mountains').should('have.value', 'Licensed magnetic Mountains');

      cy.get(`[data-cy="freeField3"]`)
        .type('Berkshire bricks-and-clicks silver')
        .should('have.value', 'Berkshire bricks-and-clicks silver');

      cy.get(`[data-cy="freeField4"]`).type('Checking Borders real-time').should('have.value', 'Checking Borders real-time');

      cy.get(`[data-cy="freeField5"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField6', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T15:34').blur().should('have.value', '2023-07-25T15:34');

      cy.get(`[data-cy="createdBy"]`).type('Handmade').should('have.value', 'Handmade');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T19:13').blur().should('have.value', '2023-07-25T19:13');

      cy.get(`[data-cy="updatedBy"]`).type('Concrete').should('have.value', 'Concrete');

      cy.get(`[data-cy="errorCode"]`).select('INVALIDBILLERCODE');

      cy.get(`[data-cy="errorMessage"]`).select('TRANSACTIONINVALIDPAYMENTMETHOD');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        dTransactionSummary = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', dTransactionSummaryPageUrlPattern);
    });
  });
});
