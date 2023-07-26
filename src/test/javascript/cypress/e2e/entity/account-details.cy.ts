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

describe('AccountDetails e2e test', () => {
  const accountDetailsPageUrl = '/account-details';
  const accountDetailsPageUrlPattern = new RegExp('/account-details(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const accountDetailsSample = {
    billerId: 'Texas',
    paymentItemCode: 'Pizza Valleys Personal',
    accountName: 'Home Loan Account',
    returnCode: 'evolve',
    returnMessage: 'Car Re-engineered Checking',
    externalTXNid: 'Rustic',
    transactionDate: '2023-07-25T17:27:41.538Z',
    customerId: 'Account',
    customerProduct: 'bluetooth',
    customerType: 'Avon',
    accountCategory: 'RAM program Fiji',
    accountType: 'Fresh',
    accountNumber: 'Loop',
    phoneNumber: 'Extension convergence',
    channel: 'Games',
  };

  let accountDetails;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/account-details+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/account-details').as('postEntityRequest');
    cy.intercept('DELETE', '/api/account-details/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (accountDetails) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/account-details/${accountDetails.id}`,
      }).then(() => {
        accountDetails = undefined;
      });
    }
  });

  it('AccountDetails menu should load AccountDetails page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('account-details');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AccountDetails').should('exist');
    cy.url().should('match', accountDetailsPageUrlPattern);
  });

  describe('AccountDetails page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(accountDetailsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AccountDetails page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/account-details/new$'));
        cy.getEntityCreateUpdateHeading('AccountDetails');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountDetailsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/account-details',
          body: accountDetailsSample,
        }).then(({ body }) => {
          accountDetails = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/account-details+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [accountDetails],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(accountDetailsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AccountDetails page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('accountDetails');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountDetailsPageUrlPattern);
      });

      it('edit button click should load edit AccountDetails page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AccountDetails');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountDetailsPageUrlPattern);
      });

      it('edit button click should load edit AccountDetails page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AccountDetails');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountDetailsPageUrlPattern);
      });

      it('last delete button click should delete instance of AccountDetails', () => {
        cy.intercept('GET', '/api/account-details/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('accountDetails').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountDetailsPageUrlPattern);

        accountDetails = undefined;
      });
    });
  });

  describe('new AccountDetails page', () => {
    beforeEach(() => {
      cy.visit(`${accountDetailsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AccountDetails');
    });

    it('should create an instance of AccountDetails', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('b542dd23-50e5-4958-bcb9-1c06885f788d')
        .invoke('val')
        .should('match', new RegExp('b542dd23-50e5-4958-bcb9-1c06885f788d'));

      cy.get(`[data-cy="billerId"]`).type('collaboration portal').should('have.value', 'collaboration portal');

      cy.get(`[data-cy="paymentItemCode"]`).type('Designer parsing').should('have.value', 'Designer parsing');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Estates payment withdrawal').should('have.value', 'Estates payment withdrawal');

      cy.get(`[data-cy="amolDTransactionId"]`).type('dot-com').should('have.value', 'dot-com');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Inverse').should('have.value', 'Inverse');

      cy.get(`[data-cy="token"]`).type('Ergonomic Awesome Configurable').should('have.value', 'Ergonomic Awesome Configurable');

      cy.get(`[data-cy="transferId"]`).type('Buckinghamshire').should('have.value', 'Buckinghamshire');

      cy.get(`[data-cy="requestId"]`).type('53206').should('have.value', '53206');

      cy.get(`[data-cy="accountName"]`).type('Money Market Account').should('have.value', 'Money Market Account');

      cy.get(`[data-cy="returnCode"]`).type('Configurable Soft').should('have.value', 'Configurable Soft');

      cy.get(`[data-cy="returnMessage"]`).type('Avon').should('have.value', 'Avon');

      cy.get(`[data-cy="externalTXNid"]`).type('override').should('have.value', 'override');

      cy.get(`[data-cy="transactionDate"]`).type('2023-07-25T17:42').blur().should('have.value', '2023-07-25T17:42');

      cy.get(`[data-cy="customerId"]`).type('Guinea-Bissau').should('have.value', 'Guinea-Bissau');

      cy.get(`[data-cy="customerProduct"]`).type('Soap').should('have.value', 'Soap');

      cy.get(`[data-cy="customerType"]`).type('ivory').should('have.value', 'ivory');

      cy.get(`[data-cy="accountCategory"]`).type('portals').should('have.value', 'portals');

      cy.get(`[data-cy="accountType"]`).type('Chips Loan').should('have.value', 'Chips Loan');

      cy.get(`[data-cy="accountNumber"]`).type('Auto').should('have.value', 'Auto');

      cy.get(`[data-cy="phoneNumber"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="channel"]`).type('Quetzal').should('have.value', 'Quetzal');

      cy.get(`[data-cy="tranFreeField1"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="tranFreeField2"]`).type('generation multi-byte scale').should('have.value', 'generation multi-byte scale');

      cy.get(`[data-cy="tranFreeField3"]`).type('Estate Washington').should('have.value', 'Estate Washington');

      cy.get(`[data-cy="tranFreeField4"]`).type('South Frozen Stravenue').should('have.value', 'South Frozen Stravenue');

      cy.get(`[data-cy="tranFreeField5"]`).type('Principal withdrawal deposit').should('have.value', 'Principal withdrawal deposit');

      cy.get(`[data-cy="tranFreeField6"]`).type('methodologies').should('have.value', 'methodologies');

      cy.get(`[data-cy="tranFreeField7"]`).type('payment').should('have.value', 'payment');

      cy.get(`[data-cy="tranFreeField8"]`).type('partnerships Meadow alarm').should('have.value', 'partnerships Meadow alarm');

      cy.get(`[data-cy="tranFreeField9"]`).type('facilitate').should('have.value', 'facilitate');

      cy.get(`[data-cy="tranFreeField10"]`).type('bandwidth Marshall').should('have.value', 'bandwidth Marshall');

      cy.get(`[data-cy="tranFreeField11"]`).type('brand Dynamic wireless').should('have.value', 'brand Dynamic wireless');

      cy.get(`[data-cy="tranFreeField12"]`).type('Bacon installation green').should('have.value', 'Bacon installation green');

      cy.get(`[data-cy="tranFreeField13"]`).type('channels Account blue').should('have.value', 'channels Account blue');

      cy.get(`[data-cy="tranFreeField14"]`).type('program input reboot').should('have.value', 'program input reboot');

      cy.get(`[data-cy="tranFreeField15"]`)
        .type('Cambridgeshire monetize Massachusetts')
        .should('have.value', 'Cambridgeshire monetize Massachusetts');

      cy.get(`[data-cy="tranFreeField16"]`).type('generate').should('have.value', 'generate');

      cy.get(`[data-cy="tranFreeField17"]`).type('THX Soft JBOD').should('have.value', 'THX Soft JBOD');

      cy.get(`[data-cy="tranFreeField18"]`).type('4183').should('have.value', '4183');

      cy.get(`[data-cy="tranFreeField19"]`).type('76476').should('have.value', '76476');

      cy.get(`[data-cy="tranFreeField20"]`).should('not.be.checked');
      cy.get(`[data-cy="tranFreeField20"]`).click().should('be.checked');

      cy.get(`[data-cy="tranFreeField21"]`).type('Generic').should('have.value', 'Generic');

      cy.get(`[data-cy="tranFreeField22"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('tranFreeField23', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField24"]`).type('2023-07-25T17:53').blur().should('have.value', '2023-07-25T17:53');

      cy.get(`[data-cy="tranFreeField25"]`).type('Creek').should('have.value', 'Creek');

      cy.get(`[data-cy="tranFreeField26"]`).type('Automotive Borders').should('have.value', 'Automotive Borders');

      cy.get(`[data-cy="tranFreeField27"]`).type('Solutions Carolina override').should('have.value', 'Solutions Carolina override');

      cy.get(`[data-cy="tranFreeField28"]`).type('Nuevo copying copy').should('have.value', 'Nuevo copying copy');

      cy.get(`[data-cy="tranFreeField29"]`).type('Cross-group').should('have.value', 'Cross-group');

      cy.get(`[data-cy="tranFreeField30"]`).type('SAS').should('have.value', 'SAS');

      cy.get(`[data-cy="tranFreeField31"]`).type('Central').should('have.value', 'Central');

      cy.get(`[data-cy="tranFreeField32"]`).type('Borders e-enable Assistant').should('have.value', 'Borders e-enable Assistant');

      cy.get(`[data-cy="tranFreeField33"]`).type('e-markets').should('have.value', 'e-markets');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T17:10').blur().should('have.value', '2023-07-25T17:10');

      cy.get(`[data-cy="createdBy"]`).type('Books Steel Granite').should('have.value', 'Books Steel Granite');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T02:20').blur().should('have.value', '2023-07-25T02:20');

      cy.get(`[data-cy="updatedBy"]`).type('navigating Keyboard').should('have.value', 'navigating Keyboard');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        accountDetails = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', accountDetailsPageUrlPattern);
    });
  });
});
