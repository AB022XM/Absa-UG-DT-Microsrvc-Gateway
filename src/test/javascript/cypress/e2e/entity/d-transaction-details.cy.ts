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

describe('DTransactionDetails e2e test', () => {
  const dTransactionDetailsPageUrl = '/d-transaction-details';
  const dTransactionDetailsPageUrlPattern = new RegExp('/d-transaction-details(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dTransactionDetailsSample = {
    billerId: 'Extension',
    paymentItemCode: 'Avon Account Borders',
    productCode: 'redundant connecting deliverables',
    paymentChannelCode: 'INTERNET_BANKING',
    debitAccountNumber: 'interface',
    creditAccountNumber: 'plug-and-play architecture Loan',
    debitAmount: 80588,
    debitDate: '2023-07-25T04:40:17.496Z',
    creditDate: '2023-07-25T06:53:53.446Z',
    debitCurrency: 'multi-byte repurpose',
    phoneNumber: 'Somali',
    email: 'Monty.Pagac@yahoo.com',
  };

  let dTransactionDetails;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-transaction-details+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-transaction-details').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-transaction-details/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dTransactionDetails) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-transaction-details/${dTransactionDetails.id}`,
      }).then(() => {
        dTransactionDetails = undefined;
      });
    }
  });

  it('DTransactionDetails menu should load DTransactionDetails page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-transaction-details');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DTransactionDetails').should('exist');
    cy.url().should('match', dTransactionDetailsPageUrlPattern);
  });

  describe('DTransactionDetails page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dTransactionDetailsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DTransactionDetails page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-transaction-details/new$'));
        cy.getEntityCreateUpdateHeading('DTransactionDetails');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionDetailsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-transaction-details',
          body: dTransactionDetailsSample,
        }).then(({ body }) => {
          dTransactionDetails = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-transaction-details+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [dTransactionDetails],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(dTransactionDetailsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DTransactionDetails page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dTransactionDetails');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionDetailsPageUrlPattern);
      });

      it('edit button click should load edit DTransactionDetails page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionDetails');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionDetailsPageUrlPattern);
      });

      it('edit button click should load edit DTransactionDetails page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionDetails');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionDetailsPageUrlPattern);
      });

      it('last delete button click should delete instance of DTransactionDetails', () => {
        cy.intercept('GET', '/api/d-transaction-details/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dTransactionDetails').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionDetailsPageUrlPattern);

        dTransactionDetails = undefined;
      });
    });
  });

  describe('new DTransactionDetails page', () => {
    beforeEach(() => {
      cy.visit(`${dTransactionDetailsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DTransactionDetails');
    });

    it('should create an instance of DTransactionDetails', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('f44745f8-c8e8-49b5-8445-f49645b8dd56')
        .invoke('val')
        .should('match', new RegExp('f44745f8-c8e8-49b5-8445-f49645b8dd56'));

      cy.get(`[data-cy="billerId"]`).type('Field XSS').should('have.value', 'Field XSS');

      cy.get(`[data-cy="paymentItemCode"]`).type('fuchsia').should('have.value', 'fuchsia');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Small capacitor').should('have.value', 'Small capacitor');

      cy.get(`[data-cy="amolDTransactionId"]`).type('Corporate coherent Gloves').should('have.value', 'Corporate coherent Gloves');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Afghani').should('have.value', 'Afghani');

      cy.get(`[data-cy="token"]`).type('invoice redundant').should('have.value', 'invoice redundant');

      cy.get(`[data-cy="transferId"]`).type('intuitive Massachusetts Borders').should('have.value', 'intuitive Massachusetts Borders');

      cy.get(`[data-cy="productCode"]`).type('Shoes Bacon').should('have.value', 'Shoes Bacon');

      cy.get(`[data-cy="paymentChannelCode"]`).select('MOBILE_BANKING');

      cy.get(`[data-cy="debitAccountNumber"]`)
        .type('connecting front-end Fantastic')
        .should('have.value', 'connecting front-end Fantastic');

      cy.get(`[data-cy="creditAccountNumber"]`).type('CSS benchmark').should('have.value', 'CSS benchmark');

      cy.get(`[data-cy="debitAmount"]`).type('53220').should('have.value', '53220');

      cy.get(`[data-cy="creditAmount"]`).type('95666').should('have.value', '95666');

      cy.get(`[data-cy="transactionAmount"]`).type('34206').should('have.value', '34206');

      cy.get(`[data-cy="debitDate"]`).type('2023-07-25T10:32').blur().should('have.value', '2023-07-25T10:32');

      cy.get(`[data-cy="creditDate"]`).type('2023-07-25T03:36').blur().should('have.value', '2023-07-25T03:36');

      cy.get(`[data-cy="status"]`).select('PENDING');

      cy.get(`[data-cy="settlementDate"]`).type('2023-07-25T00:41').blur().should('have.value', '2023-07-25T00:41');

      cy.get(`[data-cy="debitCurrency"]`).type('Borders Barbados').should('have.value', 'Borders Barbados');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T12:30').blur().should('have.value', '2023-07-25T12:30');

      cy.get(`[data-cy="phoneNumber"]`).type('frictionless Missouri Mandatory').should('have.value', 'frictionless Missouri Mandatory');

      cy.get(`[data-cy="email"]`).type('Tracy_Collier@yahoo.com').should('have.value', 'Tracy_Collier@yahoo.com');

      cy.get(`[data-cy="payerName"]`).type('Delaware quantifying Designer').should('have.value', 'Delaware quantifying Designer');

      cy.get(`[data-cy="payerAddress"]`).type('Keyboard Creative extensible').should('have.value', 'Keyboard Creative extensible');

      cy.get(`[data-cy="payerEmail"]`).type('Loop demand-driven').should('have.value', 'Loop demand-driven');

      cy.get(`[data-cy="payerPhoneNumber"]`)
        .type('Handcrafted Investment overriding')
        .should('have.value', 'Handcrafted Investment overriding');

      cy.get(`[data-cy="payerNarration"]`).type('compressing Avon HTTP').should('have.value', 'compressing Avon HTTP');

      cy.get(`[data-cy="payerBranchId"]`).type('Applications Extended').should('have.value', 'Applications Extended');

      cy.get(`[data-cy="beneficiaryName"]`).type('pixel').should('have.value', 'pixel');

      cy.get(`[data-cy="beneficiaryAccount"]`).type('reboot bypassing').should('have.value', 'reboot bypassing');

      cy.get(`[data-cy="beneficiaryBranchId"]`).type('Supervisor').should('have.value', 'Supervisor');

      cy.get(`[data-cy="beneficiaryPhoneNumber"]`)
        .type('Infrastructure Keys capacitor')
        .should('have.value', 'Infrastructure Keys capacitor');

      cy.get(`[data-cy="tranStatus"]`).select('SUCCESS');

      cy.get(`[data-cy="narration1"]`).type('applications Dollar').should('have.value', 'applications Dollar');

      cy.get(`[data-cy="narration2"]`).type('Plains virtual').should('have.value', 'Plains virtual');

      cy.get(`[data-cy="narration3"]`).type('Web monetize').should('have.value', 'Web monetize');

      cy.get(`[data-cy="narration4"]`).type('Account Georgia Steel').should('have.value', 'Account Georgia Steel');

      cy.get(`[data-cy="narration5"]`).type('Awesome Pants Bedfordshire').should('have.value', 'Awesome Pants Bedfordshire');

      cy.get(`[data-cy="narration6"]`).type('technologies').should('have.value', 'technologies');

      cy.get(`[data-cy="narration7"]`).type('Pants').should('have.value', 'Pants');

      cy.get(`[data-cy="narration8"]`).type('Dinar turquoise').should('have.value', 'Dinar turquoise');

      cy.get(`[data-cy="narration9"]`).type('haptic').should('have.value', 'haptic');

      cy.get(`[data-cy="narration10"]`).type('tan panel approach').should('have.value', 'tan panel approach');

      cy.get(`[data-cy="narration11"]`).type('Keyboard teal optical').should('have.value', 'Keyboard teal optical');

      cy.get(`[data-cy="narration12"]`)
        .type('Bedfordshire enhance clear-thinking')
        .should('have.value', 'Bedfordshire enhance clear-thinking');

      cy.get(`[data-cy="modeOfPayment"]`).select('RTGS');

      cy.get(`[data-cy="retries"]`).type('42367').should('have.value', '42367');

      cy.get(`[data-cy="posted"]`).should('not.be.checked');
      cy.get(`[data-cy="posted"]`).click().should('be.checked');

      cy.get(`[data-cy="apiId"]`).type('Orchestrator').should('have.value', 'Orchestrator');

      cy.get(`[data-cy="apiVersion"]`).type('withdrawal innovate B2C').should('have.value', 'withdrawal innovate B2C');

      cy.get(`[data-cy="postingApi"]`).type('Money Soft').should('have.value', 'Money Soft');

      cy.get(`[data-cy="isPosted"]`).should('not.be.checked');
      cy.get(`[data-cy="isPosted"]`).click().should('be.checked');

      cy.get(`[data-cy="postedBy"]`).type('intuitive dedicated').should('have.value', 'intuitive dedicated');

      cy.get(`[data-cy="postedDate"]`).type('indexing Soft').should('have.value', 'indexing Soft');

      cy.get(`[data-cy="tranFreeField1"]`).type('Product ADP').should('have.value', 'Product ADP');

      cy.get(`[data-cy="tranFreeField3"]`).type('Forward hard circuit').should('have.value', 'Forward hard circuit');

      cy.get(`[data-cy="tranFreeField4"]`).type('connect cultivate Wisconsin').should('have.value', 'connect cultivate Wisconsin');

      cy.get(`[data-cy="tranFreeField5"]`).type('Netherlands Ball Borders').should('have.value', 'Netherlands Ball Borders');

      cy.get(`[data-cy="tranFreeField6"]`).type('Account connecting monitor').should('have.value', 'Account connecting monitor');

      cy.get(`[data-cy="tranFreeField7"]`).type('seize Wooden Hat').should('have.value', 'seize Wooden Hat');

      cy.get(`[data-cy="tranFreeField8"]`).type('Ball Shoes Gorgeous').should('have.value', 'Ball Shoes Gorgeous');

      cy.get(`[data-cy="tranFreeField9"]`).type('Keyboard Loan fresh-thinking').should('have.value', 'Keyboard Loan fresh-thinking');

      cy.get(`[data-cy="tranFreeField10"]`).type('exuding 24/7').should('have.value', 'exuding 24/7');

      cy.get(`[data-cy="tranFreeField11"]`).type('Compatible').should('have.value', 'Compatible');

      cy.setFieldImageAsBytesOfEntity('tranFreeField12', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField13"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="tranFreeField14"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('tranFreeField15', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="tranFreeField16"]`).type('sensor Wooden Concrete').should('have.value', 'sensor Wooden Concrete');

      cy.get(`[data-cy="tranFreeField17"]`).type('Wooden').should('have.value', 'Wooden');

      cy.get(`[data-cy="tranFreeField18"]`).type('HTTP').should('have.value', 'HTTP');

      cy.get(`[data-cy="tranFreeField19"]`).type('turquoise Handmade Money').should('have.value', 'turquoise Handmade Money');

      cy.get(`[data-cy="tranFreeField20"]`).type('methodologies Wooden Beauty').should('have.value', 'methodologies Wooden Beauty');

      cy.get(`[data-cy="tranFreeField21"]`).type('core Health').should('have.value', 'core Health');

      cy.get(`[data-cy="tranFreeField22"]`).type('Sports knowledge').should('have.value', 'Sports knowledge');

      cy.get(`[data-cy="tranFreeField23"]`).type('moderator Customer').should('have.value', 'moderator Customer');

      cy.get(`[data-cy="tranFreeField24"]`).type('access Pizza hacking').should('have.value', 'access Pizza hacking');

      cy.get(`[data-cy="tranFreeField25"]`).type('parse Kip Dinar').should('have.value', 'parse Kip Dinar');

      cy.get(`[data-cy="tranFreeField26"]`).type('white Account').should('have.value', 'white Account');

      cy.get(`[data-cy="tranFreeField27"]`).type('Quality adapter engineer').should('have.value', 'Quality adapter engineer');

      cy.get(`[data-cy="tranFreeField28"]`).type('ADP').should('have.value', 'ADP');

      cy.get(`[data-cy="internalErrorCode"]`).type('real-time').should('have.value', 'real-time');

      cy.get(`[data-cy="externalErrorCode"]`).type('Real holistic').should('have.value', 'Real holistic');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        dTransactionDetails = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', dTransactionDetailsPageUrlPattern);
    });
  });
});
