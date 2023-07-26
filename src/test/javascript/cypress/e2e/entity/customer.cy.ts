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

describe('Customer e2e test', () => {
  const customerPageUrl = '/customer';
  const customerPageUrlPattern = new RegExp('/customer(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const customerSample = {
    billerId: 'Loan web-readiness',
    paymentItemCode: 'incubate',
    accountName: 'Personal Loan Account',
    returnCode: '4th',
    returnMessage: 'Accounts niches Shoal',
    externalTXNid: 'Account Small Steel',
    transactionDate: '2023-07-25T00:09:51.737Z',
    customerId: 'seamless Fantastic Vermont',
    customerProduct: 'non-volatile',
    customerType: 'tan 24/7 infrastructures',
    accountCategory: 'unleash',
    accountType: 'calculate',
    accountNumber: 'Jersey Usability reinvent',
    phoneNumber: 'facilitate Up-sized TCP',
    channel: 'Intranet Garden',
  };

  let customer;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/customers+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/customers').as('postEntityRequest');
    cy.intercept('DELETE', '/api/customers/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (customer) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/customers/${customer.id}`,
      }).then(() => {
        customer = undefined;
      });
    }
  });

  it('Customers menu should load Customers page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('customer');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Customer').should('exist');
    cy.url().should('match', customerPageUrlPattern);
  });

  describe('Customer page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(customerPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Customer page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/customer/new$'));
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/customers',
          body: customerSample,
        }).then(({ body }) => {
          customer = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/customers+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [customer],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(customerPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Customer page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('customer');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('edit button click should load edit Customer page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('edit button click should load edit Customer page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('last delete button click should delete instance of Customer', () => {
        cy.intercept('GET', '/api/customers/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('customer').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);

        customer = undefined;
      });
    });
  });

  describe('new Customer page', () => {
    beforeEach(() => {
      cy.visit(`${customerPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Customer');
    });

    it('should create an instance of Customer', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('0c53333d-6b22-43ec-9091-5a1acad42532')
        .invoke('val')
        .should('match', new RegExp('0c53333d-6b22-43ec-9091-5a1acad42532'));

      cy.get(`[data-cy="billerId"]`).type('bandwidth Practical').should('have.value', 'bandwidth Practical');

      cy.get(`[data-cy="paymentItemCode"]`).type('value-added').should('have.value', 'value-added');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Health B2C bypassing').should('have.value', 'Health B2C bypassing');

      cy.get(`[data-cy="amolDTransactionId"]`).type('cross-platform alarm').should('have.value', 'cross-platform alarm');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('azure open-source matrix').should('have.value', 'azure open-source matrix');

      cy.get(`[data-cy="token"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="transferId"]`).type('frictionless Borders').should('have.value', 'frictionless Borders');

      cy.get(`[data-cy="requestId"]`).type('16310').should('have.value', '16310');

      cy.get(`[data-cy="accountName"]`).type('Checking Account').should('have.value', 'Checking Account');

      cy.get(`[data-cy="returnCode"]`).type('Poland').should('have.value', 'Poland');

      cy.get(`[data-cy="returnMessage"]`).type('Metrics overriding transmitting').should('have.value', 'Metrics overriding transmitting');

      cy.get(`[data-cy="externalTXNid"]`).type('cyan').should('have.value', 'cyan');

      cy.get(`[data-cy="transactionDate"]`).type('2023-07-25T00:51').blur().should('have.value', '2023-07-25T00:51');

      cy.get(`[data-cy="customerId"]`).type('Agent users').should('have.value', 'Agent users');

      cy.get(`[data-cy="customerProduct"]`).type('Clothing Outdoors').should('have.value', 'Clothing Outdoors');

      cy.get(`[data-cy="customerType"]`).type('group Associate').should('have.value', 'group Associate');

      cy.get(`[data-cy="accountCategory"]`).type('bypassing Bike').should('have.value', 'bypassing Bike');

      cy.get(`[data-cy="accountType"]`).type('out-of-the-box Drive').should('have.value', 'out-of-the-box Drive');

      cy.get(`[data-cy="accountNumber"]`).type('Avon evolve').should('have.value', 'Avon evolve');

      cy.get(`[data-cy="phoneNumber"]`).type('Profit-focused Wisconsin cyan').should('have.value', 'Profit-focused Wisconsin cyan');

      cy.get(`[data-cy="channel"]`).type('uniform Computers').should('have.value', 'uniform Computers');

      cy.get(`[data-cy="tranFreeField1"]`).type('quantifying schemas optical').should('have.value', 'quantifying schemas optical');

      cy.get(`[data-cy="tranFreeField2"]`).type('Grocery Unbranded').should('have.value', 'Grocery Unbranded');

      cy.get(`[data-cy="tranFreeField3"]`).type('Ouguiya reboot').should('have.value', 'Ouguiya reboot');

      cy.get(`[data-cy="tranFreeField4"]`).type('SSL Meadows Intuitive').should('have.value', 'SSL Meadows Intuitive');

      cy.get(`[data-cy="tranFreeField5"]`).type('Multi-lateral Account Customer').should('have.value', 'Multi-lateral Account Customer');

      cy.get(`[data-cy="tranFreeField6"]`).type('Ameliorated target').should('have.value', 'Ameliorated target');

      cy.get(`[data-cy="tranFreeField7"]`).type('Outdoors firmware yellow').should('have.value', 'Outdoors firmware yellow');

      cy.get(`[data-cy="tranFreeField8"]`).type('turquoise contingency').should('have.value', 'turquoise contingency');

      cy.get(`[data-cy="tranFreeField9"]`).type('withdrawal attitude array').should('have.value', 'withdrawal attitude array');

      cy.get(`[data-cy="tranFreeField10"]`).type('payment Small').should('have.value', 'payment Small');

      cy.get(`[data-cy="tranFreeField11"]`)
        .type('Practical Intelligent Handcrafted')
        .should('have.value', 'Practical Intelligent Handcrafted');

      cy.get(`[data-cy="tranFreeField12"]`).type('viral compress').should('have.value', 'viral compress');

      cy.get(`[data-cy="tranFreeField13"]`).type('Gloves').should('have.value', 'Gloves');

      cy.get(`[data-cy="tranFreeField14"]`)
        .type('Optimized Incredible intelligence')
        .should('have.value', 'Optimized Incredible intelligence');

      cy.get(`[data-cy="tranFreeField15"]`).type('capacitor').should('have.value', 'capacitor');

      cy.get(`[data-cy="tranFreeField16"]`).type('content Mews').should('have.value', 'content Mews');

      cy.get(`[data-cy="tranFreeField17"]`).type('cultivate Configurable Avon').should('have.value', 'cultivate Configurable Avon');

      cy.get(`[data-cy="tranFreeField18"]`).type('37062').should('have.value', '37062');

      cy.get(`[data-cy="tranFreeField19"]`).type('41382').should('have.value', '41382');

      cy.get(`[data-cy="tranFreeField20"]`).should('not.be.checked');
      cy.get(`[data-cy="tranFreeField20"]`).click().should('be.checked');

      cy.get(`[data-cy="tranFreeField21"]`).type('Assistant implement Market').should('have.value', 'Assistant implement Market');

      cy.get(`[data-cy="tranFreeField22"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('tranFreeField23', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField24"]`).type('2023-07-25T02:10').blur().should('have.value', '2023-07-25T02:10');

      cy.get(`[data-cy="tranFreeField25"]`).type('Home help-desk').should('have.value', 'Home help-desk');

      cy.get(`[data-cy="tranFreeField26"]`).type('installation Assurance Belarus').should('have.value', 'installation Assurance Belarus');

      cy.get(`[data-cy="tranFreeField27"]`).type('web-readiness Savings').should('have.value', 'web-readiness Savings');

      cy.get(`[data-cy="tranFreeField28"]`).type('Fresh').should('have.value', 'Fresh');

      cy.get(`[data-cy="tranFreeField29"]`).type('transmitting').should('have.value', 'transmitting');

      cy.get(`[data-cy="tranFreeField30"]`).type('Avon Buckinghamshire Integrated').should('have.value', 'Avon Buckinghamshire Integrated');

      cy.get(`[data-cy="tranFreeField31"]`).type('Fork Chips primary').should('have.value', 'Fork Chips primary');

      cy.get(`[data-cy="tranFreeField32"]`).type('Rubber facilitate').should('have.value', 'Rubber facilitate');

      cy.get(`[data-cy="tranFreeField33"]`).type('Ball Square Baby').should('have.value', 'Ball Square Baby');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T15:21').blur().should('have.value', '2023-07-25T15:21');

      cy.get(`[data-cy="createdBy"]`).type('navigating').should('have.value', 'navigating');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T15:16').blur().should('have.value', '2023-07-25T15:16');

      cy.get(`[data-cy="updatedBy"]`).type('parse Rustic').should('have.value', 'parse Rustic');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        customer = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', customerPageUrlPattern);
    });
  });
});
