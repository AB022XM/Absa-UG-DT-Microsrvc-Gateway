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

describe('DTransaction e2e test', () => {
  const dTransactionPageUrl = '/d-transaction';
  const dTransactionPageUrlPattern = new RegExp('/d-transaction(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dTransactionSample = {
    billerId: 'Optional',
    paymentItemCode: 'Account Multi-channelled',
    productCode: 'Infrastructure',
    paymentChannelCode: 'POS',
    accountNumber: 'monetize Manager Computers',
    amount: 78169,
    debitAmount: 10861,
    currency: 'Account',
    createdAt: '2023-07-25T00:18:14.663Z',
  };

  let dTransaction;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-transactions+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-transactions').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-transactions/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dTransaction) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-transactions/${dTransaction.id}`,
      }).then(() => {
        dTransaction = undefined;
      });
    }
  });

  it('DTransactions menu should load DTransactions page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-transaction');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DTransaction').should('exist');
    cy.url().should('match', dTransactionPageUrlPattern);
  });

  describe('DTransaction page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dTransactionPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DTransaction page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-transaction/new$'));
        cy.getEntityCreateUpdateHeading('DTransaction');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-transactions',
          body: dTransactionSample,
        }).then(({ body }) => {
          dTransaction = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-transactions+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [dTransaction],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(dTransactionPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DTransaction page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dTransaction');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionPageUrlPattern);
      });

      it('edit button click should load edit DTransaction page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransaction');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionPageUrlPattern);
      });

      it('edit button click should load edit DTransaction page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransaction');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionPageUrlPattern);
      });

      it('last delete button click should delete instance of DTransaction', () => {
        cy.intercept('GET', '/api/d-transactions/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dTransaction').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionPageUrlPattern);

        dTransaction = undefined;
      });
    });
  });

  describe('new DTransaction page', () => {
    beforeEach(() => {
      cy.visit(`${dTransactionPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DTransaction');
    });

    it('should create an instance of DTransaction', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('9cbfee7b-f51e-4380-bdd5-395b45d90c10')
        .invoke('val')
        .should('match', new RegExp('9cbfee7b-f51e-4380-bdd5-395b45d90c10'));

      cy.get(`[data-cy="billerId"]`).type('Frozen bypass').should('have.value', 'Frozen bypass');

      cy.get(`[data-cy="paymentItemCode"]`).type('payment capacitor Shoes').should('have.value', 'payment capacitor Shoes');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Investor RAM yellow').should('have.value', 'Investor RAM yellow');

      cy.get(`[data-cy="amolDTransactionId"]`).type('Kip Cotton Practical').should('have.value', 'Kip Cotton Practical');

      cy.get(`[data-cy="transactionReferenceNumber"]`)
        .type('Cambridgeshire cross-platform Expressway')
        .should('have.value', 'Cambridgeshire cross-platform Expressway');

      cy.get(`[data-cy="externalTranid"]`).type('capacitor').should('have.value', 'capacitor');

      cy.get(`[data-cy="token"]`).type('virtual').should('have.value', 'virtual');

      cy.get(`[data-cy="transferId"]`).type('Movies Chicken Shoes').should('have.value', 'Movies Chicken Shoes');

      cy.get(`[data-cy="productCode"]`).type('purple payment reboot').should('have.value', 'purple payment reboot');

      cy.get(`[data-cy="paymentChannelCode"]`).select('ATM');

      cy.get(`[data-cy="accountNumber"]`)
        .type('Crest Reverse-engineered transmit')
        .should('have.value', 'Crest Reverse-engineered transmit');

      cy.get(`[data-cy="amount"]`).type('43683').should('have.value', '43683');

      cy.get(`[data-cy="debitAmount"]`).type('47396').should('have.value', '47396');

      cy.get(`[data-cy="creditAmount"]`).type('91629').should('have.value', '91629');

      cy.get(`[data-cy="settlementAmount"]`).type('94147').should('have.value', '94147');

      cy.get(`[data-cy="settlementDate"]`).type('2023-07-25T00:57').blur().should('have.value', '2023-07-25T00:57');

      cy.get(`[data-cy="transactionDate"]`).type('2023-07-25T00:44').blur().should('have.value', '2023-07-25T00:44');

      cy.get(`[data-cy="isRetried"]`).should('not.be.checked');
      cy.get(`[data-cy="isRetried"]`).click().should('be.checked');

      cy.get(`[data-cy="lastRetryDate"]`).type('2023-07-25T06:55').blur().should('have.value', '2023-07-25T06:55');

      cy.get(`[data-cy="firstRetryDate"]`).type('2023-07-25T06:10').blur().should('have.value', '2023-07-25T06:10');

      cy.get(`[data-cy="retryResposeCode"]`).select('PENDING');

      cy.get(`[data-cy="retryResponseMessage"]`).select('TRANSACTIONINVALIDBILLERCODE');

      cy.get(`[data-cy="retryCount"]`).type('79902').should('have.value', '79902');

      cy.get(`[data-cy="isRetriableFlg"]`).should('not.be.checked');
      cy.get(`[data-cy="isRetriableFlg"]`).click().should('be.checked');

      cy.get(`[data-cy="doNotRetryDTransaction"]`).should('not.be.checked');
      cy.get(`[data-cy="doNotRetryDTransaction"]`).click().should('be.checked');

      cy.get(`[data-cy="status"]`).select('SUCCESS');

      cy.get(`[data-cy="statusCode"]`).type('black Bedfordshire vortals').should('have.value', 'black Bedfordshire vortals');

      cy.get(`[data-cy="statusDetails"]`).type('silver Operations technologies').should('have.value', 'silver Operations technologies');

      cy.get(`[data-cy="retries"]`).type('61711').should('have.value', '61711');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T07:00').blur().should('have.value', '2023-07-25T07:00');

      cy.get(`[data-cy="postedBy"]`).type('Crossing').should('have.value', 'Crossing');

      cy.get(`[data-cy="postedDate"]`).type('Account navigate Tuna').should('have.value', 'Account navigate Tuna');

      cy.get(`[data-cy="internalErrorCode"]`).type('compelling Berkshire').should('have.value', 'compelling Berkshire');

      cy.get(`[data-cy="externalErrorCode"]`).type('Rica').should('have.value', 'Rica');

      cy.get(`[data-cy="isCrossCurrency"]`).should('not.be.checked');
      cy.get(`[data-cy="isCrossCurrency"]`).click().should('be.checked');

      cy.get(`[data-cy="isCrossBank"]`).should('not.be.checked');
      cy.get(`[data-cy="isCrossBank"]`).click().should('be.checked');

      cy.get(`[data-cy="currency"]`).type('Implementation Chips').should('have.value', 'Implementation Chips');

      cy.get(`[data-cy="creditAccount"]`).type('Accountability maroon').should('have.value', 'Accountability maroon');

      cy.get(`[data-cy="debitAccount"]`).type('Quality Tasty').should('have.value', 'Quality Tasty');

      cy.get(`[data-cy="phoneNumber"]`).type('payment Tasty').should('have.value', 'payment Tasty');

      cy.get(`[data-cy="customerNumber"]`).type('foreground application').should('have.value', 'foreground application');

      cy.get(`[data-cy="tranStatus"]`).select('FAILED');

      cy.get(`[data-cy="tranStatusDetails"]`).type('blue').should('have.value', 'blue');

      cy.get(`[data-cy="tranFreeField1"]`).type('Chips Avon task-force').should('have.value', 'Chips Avon task-force');

      cy.get(`[data-cy="tranFreeField2"]`).type('program Rand Burundi').should('have.value', 'program Rand Burundi');

      cy.get(`[data-cy="tranFreeField3"]`).type('quantifying grey cross-platform').should('have.value', 'quantifying grey cross-platform');

      cy.get(`[data-cy="tranFreeField4"]`).type('Plastic Tasty').should('have.value', 'Plastic Tasty');

      cy.get(`[data-cy="tranFreeField5"]`).type('transitional Cheese XML').should('have.value', 'transitional Cheese XML');

      cy.get(`[data-cy="tranFreeField6"]`).type('eyeballs multi-byte').should('have.value', 'eyeballs multi-byte');

      cy.get(`[data-cy="tranFreeField7"]`).type('Engineer haptic cutting-edge').should('have.value', 'Engineer haptic cutting-edge');

      cy.get(`[data-cy="tranFreeField8"]`).type('Borders').should('have.value', 'Borders');

      cy.get(`[data-cy="tranFreeField9"]`).type('Manager Arizona digital').should('have.value', 'Manager Arizona digital');

      cy.get(`[data-cy="tranFreeField10"]`).type('invoice').should('have.value', 'invoice');

      cy.get(`[data-cy="tranFreeField11"]`).type('Frozen').should('have.value', 'Frozen');

      cy.get(`[data-cy="tranFreeField12"]`).type('Tasty Steel').should('have.value', 'Tasty Steel');

      cy.get(`[data-cy="tranFreeField13"]`).type('83353').should('have.value', '83353');

      cy.setFieldImageAsBytesOfEntity('tranFreeField14', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField15"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="tranFreeField16"]`).type('2023-07-25T05:57').blur().should('have.value', '2023-07-25T05:57');

      cy.get(`[data-cy="tranFreeField17"]`).should('not.be.checked');
      cy.get(`[data-cy="tranFreeField17"]`).click().should('be.checked');

      cy.get(`[data-cy="tranFreeField18"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="tranFreeField19"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="tranFreeField20"]`).type('2023-07-25T05:40').blur().should('have.value', '2023-07-25T05:40');

      cy.get(`[data-cy="tranFreeField21"]`).type('2023-07-25T11:30').blur().should('have.value', '2023-07-25T11:30');

      cy.get(`[data-cy="tranFreeField22"]`).should('not.be.checked');
      cy.get(`[data-cy="tranFreeField22"]`).click().should('be.checked');

      cy.get(`[data-cy="tranFreeField23"]`).type('2023-07-25T00:02').blur().should('have.value', '2023-07-25T00:02');

      cy.get(`[data-cy="tranFreeField24"]`).type('Computers mobile').should('have.value', 'Computers mobile');

      cy.get(`[data-cy="tranFreeField25"]`).type('cross-media Gloves compressing').should('have.value', 'cross-media Gloves compressing');

      cy.get(`[data-cy="tranFreeField26"]`).type('Incredible SCSI Saint').should('have.value', 'Incredible SCSI Saint');

      cy.get(`[data-cy="tranFreeField27"]`).type('transform Lakes Cambridgeshire').should('have.value', 'transform Lakes Cambridgeshire');

      cy.get(`[data-cy="tranFreeField28"]`).type('Refined AGP invoice').should('have.value', 'Refined AGP invoice');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T12:53').blur().should('have.value', '2023-07-25T12:53');

      cy.get(`[data-cy="createdBy"]`).type('Handcrafted Self-enabling').should('have.value', 'Handcrafted Self-enabling');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T15:35').blur().should('have.value', '2023-07-25T15:35');

      cy.get(`[data-cy="updatedBy"]`).type('Forward').should('have.value', 'Forward');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        dTransaction = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', dTransactionPageUrlPattern);
    });
  });
});
