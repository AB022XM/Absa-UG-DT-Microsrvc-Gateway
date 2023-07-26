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

describe('AmolDTransactions e2e test', () => {
  const amolDTransactionsPageUrl = '/amol-d-transactions';
  const amolDTransactionsPageUrlPattern = new RegExp('/amol-d-transactions(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const amolDTransactionsSample = {
    debitAccountNumber: 'monitor Bedfordshire Fantastic',
    creditAccountNumber: 'Arab override',
    debitAmount: 6916,
    debitCurrency: 'EUR',
    timestamp: '2023-07-25T13:26:00.229Z',
    phoneNumber: 'Bedfordshire',
    email: 'Gloria.Bahringer@hotmail.com',
  };

  let amolDTransactions;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/amol-d-transactions+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/amol-d-transactions').as('postEntityRequest');
    cy.intercept('DELETE', '/api/amol-d-transactions/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (amolDTransactions) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/amol-d-transactions/${amolDTransactions.id}`,
      }).then(() => {
        amolDTransactions = undefined;
      });
    }
  });

  it('AmolDTransactions menu should load AmolDTransactions page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('amol-d-transactions');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AmolDTransactions').should('exist');
    cy.url().should('match', amolDTransactionsPageUrlPattern);
  });

  describe('AmolDTransactions page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(amolDTransactionsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AmolDTransactions page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/amol-d-transactions/new$'));
        cy.getEntityCreateUpdateHeading('AmolDTransactions');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', amolDTransactionsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/amol-d-transactions',
          body: amolDTransactionsSample,
        }).then(({ body }) => {
          amolDTransactions = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/amol-d-transactions+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [amolDTransactions],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(amolDTransactionsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AmolDTransactions page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('amolDTransactions');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', amolDTransactionsPageUrlPattern);
      });

      it('edit button click should load edit AmolDTransactions page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AmolDTransactions');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', amolDTransactionsPageUrlPattern);
      });

      it('edit button click should load edit AmolDTransactions page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AmolDTransactions');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', amolDTransactionsPageUrlPattern);
      });

      it('last delete button click should delete instance of AmolDTransactions', () => {
        cy.intercept('GET', '/api/amol-d-transactions/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('amolDTransactions').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', amolDTransactionsPageUrlPattern);

        amolDTransactions = undefined;
      });
    });
  });

  describe('new AmolDTransactions page', () => {
    beforeEach(() => {
      cy.visit(`${amolDTransactionsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AmolDTransactions');
    });

    it('should create an instance of AmolDTransactions', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('c703fe16-1841-4f6c-aa3e-515d2ab7b018')
        .invoke('val')
        .should('match', new RegExp('c703fe16-1841-4f6c-aa3e-515d2ab7b018'));

      cy.get(`[data-cy="billerId"]`).type('compress Pizza').should('have.value', 'compress Pizza');

      cy.get(`[data-cy="dtDTransactionId"]`)
        .type('Fresh demand-driven cross-platform')
        .should('have.value', 'Fresh demand-driven cross-platform');

      cy.get(`[data-cy="amolDTransactionId"]`).type('Outdoors Wooden payment').should('have.value', 'Outdoors Wooden payment');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('reintermediate Dobra').should('have.value', 'reintermediate Dobra');

      cy.get(`[data-cy="token"]`).type('Avon SMS').should('have.value', 'Avon SMS');

      cy.get(`[data-cy="transferId"]`).type('even-keeled').should('have.value', 'even-keeled');

      cy.get(`[data-cy="externalTxnId"]`).type('Intranet content Gorgeous').should('have.value', 'Intranet content Gorgeous');

      cy.get(`[data-cy="customerReference"]`).type('Engineer Customer Toys').should('have.value', 'Engineer Customer Toys');

      cy.get(`[data-cy="debitAccountNumber"]`)
        .type('Grocery Fundamental Handcrafted')
        .should('have.value', 'Grocery Fundamental Handcrafted');

      cy.get(`[data-cy="creditAccountNumber"]`).type('Cotton multi-byte').should('have.value', 'Cotton multi-byte');

      cy.get(`[data-cy="debitAmount"]`).type('84828').should('have.value', '84828');

      cy.get(`[data-cy="creditAmount"]`).type('86462').should('have.value', '86462');

      cy.get(`[data-cy="transactionAmount"]`).type('42619').should('have.value', '42619');

      cy.get(`[data-cy="debitDate"]`).type('2023-07-25T04:36').blur().should('have.value', '2023-07-25T04:36');

      cy.get(`[data-cy="creditDate"]`).type('2023-07-24T23:20').blur().should('have.value', '2023-07-24T23:20');

      cy.get(`[data-cy="status"]`).select('UNKNOWN');

      cy.get(`[data-cy="settlementDate"]`).type('2023-07-25T09:56').blur().should('have.value', '2023-07-25T09:56');

      cy.get(`[data-cy="debitCurrency"]`).select('AUD');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T08:40').blur().should('have.value', '2023-07-25T08:40');

      cy.get(`[data-cy="phoneNumber"]`).type('tangible').should('have.value', 'tangible');

      cy.get(`[data-cy="email"]`).type('Elton35@gmail.com').should('have.value', 'Elton35@gmail.com');

      cy.get(`[data-cy="payerName"]`).type('Oregon embrace').should('have.value', 'Oregon embrace');

      cy.get(`[data-cy="payerAddress"]`).type('Cambridgeshire').should('have.value', 'Cambridgeshire');

      cy.get(`[data-cy="payerEmail"]`).type('Rupiah').should('have.value', 'Rupiah');

      cy.get(`[data-cy="payerPhoneNumber"]`).type('Kentucky generating').should('have.value', 'Kentucky generating');

      cy.get(`[data-cy="payerNarration"]`).type('asymmetric withdrawal Sports').should('have.value', 'asymmetric withdrawal Sports');

      cy.get(`[data-cy="payerBranchId"]`)
        .type('Island cross-platform Configurable')
        .should('have.value', 'Island cross-platform Configurable');

      cy.get(`[data-cy="beneficiaryName"]`).type('Platinum').should('have.value', 'Platinum');

      cy.get(`[data-cy="beneficiaryAccount"]`).type('Investment').should('have.value', 'Investment');

      cy.get(`[data-cy="beneficiaryBranchId"]`).type('Keyboard Trinidad').should('have.value', 'Keyboard Trinidad');

      cy.get(`[data-cy="beneficiaryPhoneNumber"]`).type('hack').should('have.value', 'hack');

      cy.get(`[data-cy="tranStatus"]`).select('SUCCESS');

      cy.get(`[data-cy="narration1"]`).type('Generic navigating Director').should('have.value', 'Generic navigating Director');

      cy.get(`[data-cy="narration2"]`).type('lavender').should('have.value', 'lavender');

      cy.get(`[data-cy="narration3"]`).type('Louisiana Mauritius').should('have.value', 'Louisiana Mauritius');

      cy.get(`[data-cy="taxAmount"]`).type('9694').should('have.value', '9694');

      cy.get(`[data-cy="taxGLAccount"]`).type('Grocery Regional Motorway').should('have.value', 'Grocery Regional Motorway');

      cy.get(`[data-cy="taxNarration"]`).type('object-oriented Markets Turnpike').should('have.value', 'object-oriented Markets Turnpike');

      cy.get(`[data-cy="internalErrorCode"]`).type('Ergonomic').should('have.value', 'Ergonomic');

      cy.get(`[data-cy="externalErrorCode"]`).type('Chips Iowa azure').should('have.value', 'Chips Iowa azure');

      cy.get(`[data-cy="payeeBranchId"]`).type('USB').should('have.value', 'USB');

      cy.get(`[data-cy="payeeProductInstanceReference"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="payeeProductCode"]`).type('Trafficway azure integrate').should('have.value', 'Trafficway azure integrate');

      cy.get(`[data-cy="payeeProductName"]`).type('connecting').should('have.value', 'connecting');

      cy.get(`[data-cy="payeeProductDescription"]`).type('Avon Reactive').should('have.value', 'Avon Reactive');

      cy.get(`[data-cy="payeeProductUnitOfMeasure"]`).type('optical Games payment').should('have.value', 'optical Games payment');

      cy.get(`[data-cy="payeeProductQuantity"]`).type('bypass').should('have.value', 'bypass');

      cy.get(`[data-cy="subCategoryCode"]`).type('capacity Table').should('have.value', 'capacity Table');

      cy.get(`[data-cy="payerBankCode"]`).type('impactful').should('have.value', 'impactful');

      cy.get(`[data-cy="payerBankName"]`).type('invoice').should('have.value', 'invoice');

      cy.get(`[data-cy="payerBankAddress"]`).type('Garden Shirt Multi-tiered').should('have.value', 'Garden Shirt Multi-tiered');

      cy.get(`[data-cy="payerBankCity"]`).type('Tasty Ranch').should('have.value', 'Tasty Ranch');

      cy.get(`[data-cy="payerBankState"]`).type('grey Response Optimization').should('have.value', 'grey Response Optimization');

      cy.get(`[data-cy="payerBankCountry"]`).type('Orchestrator override olive').should('have.value', 'Orchestrator override olive');

      cy.get(`[data-cy="payerBankPostalCode"]`).type('redefine').should('have.value', 'redefine');

      cy.get(`[data-cy="checkerId"]`).type('impactful enable Shoes').should('have.value', 'impactful enable Shoes');

      cy.get(`[data-cy="remittanceInformation"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="paymentChannelCode"]`).select('POS');

      cy.get(`[data-cy="feeAmount"]`).type('44861').should('have.value', '44861');

      cy.get(`[data-cy="feeGLAccount"]`).type('wireless Buckinghamshire').should('have.value', 'wireless Buckinghamshire');

      cy.get(`[data-cy="feeNarration"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="tranFreeField1"]`).type('Generic Licensed dot-com').should('have.value', 'Generic Licensed dot-com');

      cy.get(`[data-cy="tranFreeField2"]`).type('alarm Plains').should('have.value', 'alarm Plains');

      cy.get(`[data-cy="tranFreeField3"]`).type('withdrawal invoice Circles').should('have.value', 'withdrawal invoice Circles');

      cy.get(`[data-cy="tranFreeField4"]`).type('plum').should('have.value', 'plum');

      cy.get(`[data-cy="tranFreeField5"]`).type('GB Soft Sausages').should('have.value', 'GB Soft Sausages');

      cy.get(`[data-cy="tranFreeField6"]`).type('Planner Bedfordshire connect').should('have.value', 'Planner Bedfordshire connect');

      cy.get(`[data-cy="tranFreeField7"]`).type('Islands').should('have.value', 'Islands');

      cy.get(`[data-cy="tranFreeField8"]`).type('port Hat driver').should('have.value', 'port Hat driver');

      cy.get(`[data-cy="tranFreeField9"]`).type('sensor Chicken cross-platform').should('have.value', 'sensor Chicken cross-platform');

      cy.get(`[data-cy="tranFreeField10"]`).type('Future').should('have.value', 'Future');

      cy.get(`[data-cy="tranFreeField11"]`).type('Mali').should('have.value', 'Mali');

      cy.get(`[data-cy="tranFreeField12"]`).type('transmitter').should('have.value', 'transmitter');

      cy.get(`[data-cy="tranFreeField13"]`).type('Unbranded Djibouti').should('have.value', 'Unbranded Djibouti');

      cy.get(`[data-cy="tranFreeField14"]`).type('bandwidth User-centric').should('have.value', 'bandwidth User-centric');

      cy.get(`[data-cy="tranFreeField15"]`).type('brand').should('have.value', 'brand');

      cy.get(`[data-cy="tranFreeField16"]`).type('JBOD International').should('have.value', 'JBOD International');

      cy.get(`[data-cy="tranFreeField17"]`).type('static').should('have.value', 'static');

      cy.get(`[data-cy="tranFreeField18"]`).type('15395').should('have.value', '15395');

      cy.get(`[data-cy="tranFreeField19"]`).type('28666').should('have.value', '28666');

      cy.get(`[data-cy="tranFreeField20"]`).should('not.be.checked');
      cy.get(`[data-cy="tranFreeField20"]`).click().should('be.checked');

      cy.get(`[data-cy="tranFreeField21"]`).type('Afghanistan Radial').should('have.value', 'Afghanistan Radial');

      cy.get(`[data-cy="tranFreeField22"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('tranFreeField23', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField24"]`).type('2023-07-25T06:29').blur().should('have.value', '2023-07-25T06:29');

      cy.get(`[data-cy="responseCode"]`).type('back-end').should('have.value', 'back-end');

      cy.get(`[data-cy="responseMessage"]`).type('Automotive').should('have.value', 'Automotive');

      cy.get(`[data-cy="responseDetails"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="transferReferenceId"]`).type('driver array').should('have.value', 'driver array');

      cy.get(`[data-cy="transferStatus"]`).type('Chips').should('have.value', 'Chips');

      cy.get(`[data-cy="responseDateTime"]`).type('2023-07-25T00:59').blur().should('have.value', '2023-07-25T00:59');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-24T23:50').blur().should('have.value', '2023-07-24T23:50');

      cy.get(`[data-cy="createdBy"]`).type('Administrator Cameroon').should('have.value', 'Administrator Cameroon');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T12:11').blur().should('have.value', '2023-07-25T12:11');

      cy.get(`[data-cy="updatedBy"]`).type('Music bypassing Wooden').should('have.value', 'Music bypassing Wooden');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        amolDTransactions = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', amolDTransactionsPageUrlPattern);
    });
  });
});
