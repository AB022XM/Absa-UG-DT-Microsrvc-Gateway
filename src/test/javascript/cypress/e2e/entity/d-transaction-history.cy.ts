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

describe('DTransactionHistory e2e test', () => {
  const dTransactionHistoryPageUrl = '/d-transaction-history';
  const dTransactionHistoryPageUrlPattern = new RegExp('/d-transaction-history(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dTransactionHistorySample = {
    productCode: 'drive Barbados deposit',
    paymentChannelCode: 'MOBILE_BANKING',
    debitAccountNumber: 'virtual',
    creditAccountNumber: 'SMS Web Inverse',
    debitDate: '2023-07-25T20:10:50.453Z',
    creditDate: '2023-07-25T11:45:03.859Z',
    timestamp: '2023-07-25T19:17:11.627Z',
    phoneNumber: 'Toys Grove transmit',
    email: 'Abdullah_Shields@hotmail.com',
  };

  let dTransactionHistory;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-transaction-histories+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-transaction-histories').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-transaction-histories/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dTransactionHistory) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-transaction-histories/${dTransactionHistory.id}`,
      }).then(() => {
        dTransactionHistory = undefined;
      });
    }
  });

  it('DTransactionHistories menu should load DTransactionHistories page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-transaction-history');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DTransactionHistory').should('exist');
    cy.url().should('match', dTransactionHistoryPageUrlPattern);
  });

  describe('DTransactionHistory page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dTransactionHistoryPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DTransactionHistory page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-transaction-history/new$'));
        cy.getEntityCreateUpdateHeading('DTransactionHistory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionHistoryPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-transaction-histories',
          body: dTransactionHistorySample,
        }).then(({ body }) => {
          dTransactionHistory = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-transaction-histories+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [dTransactionHistory],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(dTransactionHistoryPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DTransactionHistory page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dTransactionHistory');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionHistoryPageUrlPattern);
      });

      it('edit button click should load edit DTransactionHistory page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionHistory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionHistoryPageUrlPattern);
      });

      it('edit button click should load edit DTransactionHistory page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionHistory');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionHistoryPageUrlPattern);
      });

      it('last delete button click should delete instance of DTransactionHistory', () => {
        cy.intercept('GET', '/api/d-transaction-histories/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dTransactionHistory').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionHistoryPageUrlPattern);

        dTransactionHistory = undefined;
      });
    });
  });

  describe('new DTransactionHistory page', () => {
    beforeEach(() => {
      cy.visit(`${dTransactionHistoryPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DTransactionHistory');
    });

    it('should create an instance of DTransactionHistory', () => {
      cy.get(`[data-cy="recordId"]`).type('Awesome').should('have.value', 'Awesome');

      cy.get(`[data-cy="transferId"]`).type('parsing').should('have.value', 'parsing');

      cy.get(`[data-cy="productCode"]`).type('methodologies').should('have.value', 'methodologies');

      cy.get(`[data-cy="paymentChannelCode"]`).select('MOBILE_BANKING');

      cy.get(`[data-cy="debitAccountNumber"]`).type('archive').should('have.value', 'archive');

      cy.get(`[data-cy="creditAccountNumber"]`).type('gold mobile').should('have.value', 'gold mobile');

      cy.get(`[data-cy="debitAmount"]`).type('87823').should('have.value', '87823');

      cy.get(`[data-cy="creditAmount"]`).type('35872').should('have.value', '35872');

      cy.get(`[data-cy="transactionAmount"]`).type('38241').should('have.value', '38241');

      cy.get(`[data-cy="debitDate"]`).type('2023-07-25T15:32').blur().should('have.value', '2023-07-25T15:32');

      cy.get(`[data-cy="creditDate"]`).type('2023-07-25T11:09').blur().should('have.value', '2023-07-25T11:09');

      cy.get(`[data-cy="status"]`).select('PENDING');

      cy.get(`[data-cy="settlementDate"]`).type('2023-07-24T23:21').blur().should('have.value', '2023-07-24T23:21');

      cy.get(`[data-cy="debitCurrency"]`).select('EUR');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T13:58').blur().should('have.value', '2023-07-25T13:58');

      cy.get(`[data-cy="phoneNumber"]`).type('Granite copying Texas').should('have.value', 'Granite copying Texas');

      cy.get(`[data-cy="email"]`).type('Gage.Buckridge40@gmail.com').should('have.value', 'Gage.Buckridge40@gmail.com');

      cy.get(`[data-cy="payerName"]`).type('Shores').should('have.value', 'Shores');

      cy.get(`[data-cy="payerAddress"]`).type('Granite Accounts').should('have.value', 'Granite Accounts');

      cy.get(`[data-cy="payerEmail"]`).type('Mill attitude-oriented').should('have.value', 'Mill attitude-oriented');

      cy.get(`[data-cy="payerPhoneNumber"]`).type('Borders driver Liechtenstein').should('have.value', 'Borders driver Liechtenstein');

      cy.get(`[data-cy="payerNarration"]`).type('wireless').should('have.value', 'wireless');

      cy.get(`[data-cy="payerBranchId"]`).type('up Refined teal').should('have.value', 'up Refined teal');

      cy.get(`[data-cy="beneficiaryName"]`).type('array up withdrawal').should('have.value', 'array up withdrawal');

      cy.get(`[data-cy="beneficiaryAccount"]`).type('Gardens Steel Solutions').should('have.value', 'Gardens Steel Solutions');

      cy.get(`[data-cy="beneficiaryBranchId"]`)
        .type('discrete Executive world-class')
        .should('have.value', 'discrete Executive world-class');

      cy.get(`[data-cy="beneficiaryPhoneNumber"]`).type('Account feed').should('have.value', 'Account feed');

      cy.get(`[data-cy="tranStatus"]`).select('UNKNOWN');

      cy.get(`[data-cy="narration1"]`).type('Supervisor Granite').should('have.value', 'Supervisor Granite');

      cy.get(`[data-cy="narration2"]`).type('Egyptian bricks-and-clicks 24/7').should('have.value', 'Egyptian bricks-and-clicks 24/7');

      cy.get(`[data-cy="narration3"]`).type('Pizza').should('have.value', 'Pizza');

      cy.get(`[data-cy="modeOfPayment"]`).select('BANK_TRANSFER');

      cy.get(`[data-cy="retries"]`).type('11074').should('have.value', '11074');

      cy.get(`[data-cy="posted"]`).should('not.be.checked');
      cy.get(`[data-cy="posted"]`).click().should('be.checked');

      cy.get(`[data-cy="apiId"]`).type('Incredible').should('have.value', 'Incredible');

      cy.get(`[data-cy="apiVersion"]`).type('24/365').should('have.value', '24/365');

      cy.get(`[data-cy="postingApi"]`).type('Ariary Rustic Ports').should('have.value', 'Ariary Rustic Ports');

      cy.get(`[data-cy="isPosted"]`).should('not.be.checked');
      cy.get(`[data-cy="isPosted"]`).click().should('be.checked');

      cy.get(`[data-cy="postedBy"]`).type('Open-source').should('have.value', 'Open-source');

      cy.get(`[data-cy="postedDate"]`).type('Balanced application calculate').should('have.value', 'Balanced application calculate');

      cy.get(`[data-cy="internalErrorCode"]`).type('Investment input Metal').should('have.value', 'Investment input Metal');

      cy.get(`[data-cy="externalErrorCode"]`).type('drive backing Quality').should('have.value', 'drive backing Quality');

      cy.get(`[data-cy="tranFreeField1"]`).type('Account Planner Quetzal').should('have.value', 'Account Planner Quetzal');

      cy.get(`[data-cy="tranFreeField3"]`).type('Taiwan COM').should('have.value', 'Taiwan COM');

      cy.get(`[data-cy="tranFreeField4"]`).type('Versatile green').should('have.value', 'Versatile green');

      cy.get(`[data-cy="tranFreeField5"]`).type('out-of-the-box District').should('have.value', 'out-of-the-box District');

      cy.get(`[data-cy="tranFreeField6"]`).type('Indiana Cheese Account').should('have.value', 'Indiana Cheese Account');

      cy.get(`[data-cy="tranFreeField7"]`).type('methodical Tennessee Computer').should('have.value', 'methodical Tennessee Computer');

      cy.get(`[data-cy="tranFreeField8"]`).type('4th white').should('have.value', '4th white');

      cy.get(`[data-cy="tranFreeField9"]`).type('Bedfordshire invoice').should('have.value', 'Bedfordshire invoice');

      cy.get(`[data-cy="tranFreeField10"]`).type('IB Borders').should('have.value', 'IB Borders');

      cy.get(`[data-cy="tranFreeField11"]`).type('Fish generate').should('have.value', 'Fish generate');

      cy.get(`[data-cy="tranFreeField12"]`).type('protocol').should('have.value', 'protocol');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T12:30').blur().should('have.value', '2023-07-25T12:30');

      cy.get(`[data-cy="createdBy"]`).type('Metal New technologies').should('have.value', 'Metal New technologies');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T03:31').blur().should('have.value', '2023-07-25T03:31');

      cy.get(`[data-cy="updatedBy"]`).type('green digital National').should('have.value', 'green digital National');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        dTransactionHistory = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', dTransactionHistoryPageUrlPattern);
    });
  });
});
