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

describe('BlockedAccounts e2e test', () => {
  const blockedAccountsPageUrl = '/blocked-accounts';
  const blockedAccountsPageUrlPattern = new RegExp('/blocked-accounts(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const blockedAccountsSample = {
    customerId: 'HTTP',
    customerAccountNumber: 'hard protocol',
    blockId: 'Rubber',
    blockCode: 'salmon',
    blockDate: '2023-07-25T09:05:54.466Z',
    blockType: 'Directives Rwanda Carolina',
    blockStatus: 'parsing Fresh',
    blockReason: 'quantifying',
    blockReasonCode1: 'Implementation',
    blockReasonCode2: 'Virginia Car',
    blockReason1: 'Frozen',
    blockReasonCode3: 'generating Gorgeous',
    blockReasonCode4: 'transmit Kids',
    startDate: '2023-07-25T20:15:55.367Z',
    endDate: '2023-07-25T09:26:16.606Z',
    createdAt: '2023-07-25T15:57:34.081Z',
  };

  let blockedAccounts;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/blocked-accounts+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/blocked-accounts').as('postEntityRequest');
    cy.intercept('DELETE', '/api/blocked-accounts/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (blockedAccounts) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/blocked-accounts/${blockedAccounts.id}`,
      }).then(() => {
        blockedAccounts = undefined;
      });
    }
  });

  it('BlockedAccounts menu should load BlockedAccounts page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('blocked-accounts');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('BlockedAccounts').should('exist');
    cy.url().should('match', blockedAccountsPageUrlPattern);
  });

  describe('BlockedAccounts page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(blockedAccountsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create BlockedAccounts page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/blocked-accounts/new$'));
        cy.getEntityCreateUpdateHeading('BlockedAccounts');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', blockedAccountsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/blocked-accounts',
          body: blockedAccountsSample,
        }).then(({ body }) => {
          blockedAccounts = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/blocked-accounts+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [blockedAccounts],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(blockedAccountsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details BlockedAccounts page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('blockedAccounts');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', blockedAccountsPageUrlPattern);
      });

      it('edit button click should load edit BlockedAccounts page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('BlockedAccounts');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', blockedAccountsPageUrlPattern);
      });

      it('edit button click should load edit BlockedAccounts page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('BlockedAccounts');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', blockedAccountsPageUrlPattern);
      });

      it('last delete button click should delete instance of BlockedAccounts', () => {
        cy.intercept('GET', '/api/blocked-accounts/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('blockedAccounts').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', blockedAccountsPageUrlPattern);

        blockedAccounts = undefined;
      });
    });
  });

  describe('new BlockedAccounts page', () => {
    beforeEach(() => {
      cy.visit(`${blockedAccountsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('BlockedAccounts');
    });

    it('should create an instance of BlockedAccounts', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('610c5df0-996e-47fd-b846-3208912666ea')
        .invoke('val')
        .should('match', new RegExp('610c5df0-996e-47fd-b846-3208912666ea'));

      cy.get(`[data-cy="customerId"]`).type('Refined').should('have.value', 'Refined');

      cy.get(`[data-cy="customerAccountNumber"]`).type('markets').should('have.value', 'markets');

      cy.get(`[data-cy="dtDTransactionId"]`).type('multi-byte').should('have.value', 'multi-byte');

      cy.get(`[data-cy="blockId"]`).type('Rubber HTTP bypassing').should('have.value', 'Rubber HTTP bypassing');

      cy.get(`[data-cy="blockCode"]`).type('Steel').should('have.value', 'Steel');

      cy.get(`[data-cy="blockDate"]`).type('2023-07-25T05:53').blur().should('have.value', '2023-07-25T05:53');

      cy.get(`[data-cy="blockType"]`).type('Human').should('have.value', 'Human');

      cy.get(`[data-cy="blockStatus"]`).type('methodologies').should('have.value', 'methodologies');

      cy.get(`[data-cy="blockReason"]`).type('killer').should('have.value', 'killer');

      cy.get(`[data-cy="blockReasonCode1"]`).type('Loan').should('have.value', 'Loan');

      cy.get(`[data-cy="blockReasonCode2"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="blockReason1"]`).type('navigate').should('have.value', 'navigate');

      cy.get(`[data-cy="blockReasonCode3"]`).type('Flat').should('have.value', 'Flat');

      cy.get(`[data-cy="blockReasonCode4"]`).type('alarm').should('have.value', 'alarm');

      cy.get(`[data-cy="startDate"]`).type('2023-07-25T05:58').blur().should('have.value', '2023-07-25T05:58');

      cy.get(`[data-cy="endDate"]`).type('2023-07-25T08:02').blur().should('have.value', '2023-07-25T08:02');

      cy.get(`[data-cy="isTemp"]`).should('not.be.checked');
      cy.get(`[data-cy="isTemp"]`).click().should('be.checked');

      cy.get(`[data-cy="blockFreeText"]`).type('transmit Checking').should('have.value', 'transmit Checking');

      cy.get(`[data-cy="blockFreeText1"]`).type('Principal payment').should('have.value', 'Principal payment');

      cy.get(`[data-cy="blockFreeText2"]`).type('Technician').should('have.value', 'Technician');

      cy.get(`[data-cy="blockFreeText3"]`).type('Metal').should('have.value', 'Metal');

      cy.get(`[data-cy="blockFreeText4"]`).type('connecting card').should('have.value', 'connecting card');

      cy.get(`[data-cy="blockFreeText5"]`).type('monetize iterate').should('have.value', 'monetize iterate');

      cy.get(`[data-cy="blockFreeText6"]`).type('card virtual').should('have.value', 'card virtual');

      cy.get(`[data-cy="blockReasonCode5"]`).type('Functionality Sri Metal').should('have.value', 'Functionality Sri Metal');

      cy.get(`[data-cy="blockReasonCode6"]`).type('Reactive online efficient').should('have.value', 'Reactive online efficient');

      cy.get(`[data-cy="blockReasonCode7"]`).type('SCSI granular').should('have.value', 'SCSI granular');

      cy.get(`[data-cy="blockReasonCode8"]`).type('overriding program e-business').should('have.value', 'overriding program e-business');

      cy.get(`[data-cy="blockReasonCode9"]`).type('Concrete attitude calculate').should('have.value', 'Concrete attitude calculate');

      cy.get(`[data-cy="blockReasonCode10"]`).type('driver').should('have.value', 'driver');

      cy.get(`[data-cy="blockReasonCode11"]`).type('target Investment Division').should('have.value', 'target Investment Division');

      cy.get(`[data-cy="blockReasonCode12"]`).type('web-enabled').should('have.value', 'web-enabled');

      cy.get(`[data-cy="blockReasonCode13"]`).type('Creative Usability').should('have.value', 'Creative Usability');

      cy.get(`[data-cy="blockReasonCode14"]`).type('Devolved Gorgeous').should('have.value', 'Devolved Gorgeous');

      cy.get(`[data-cy="blockReasonCode15"]`).type('Cotton invoice Ergonomic').should('have.value', 'Cotton invoice Ergonomic');

      cy.get(`[data-cy="blockReasonCode16"]`).type('International').should('have.value', 'International');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T04:56').blur().should('have.value', '2023-07-25T04:56');

      cy.get(`[data-cy="createdBy"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T10:20').blur().should('have.value', '2023-07-25T10:20');

      cy.get(`[data-cy="updatedBy"]`).type('Brand Forward').should('have.value', 'Brand Forward');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="status"]`).select('PENDING');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        blockedAccounts = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', blockedAccountsPageUrlPattern);
    });
  });
});
