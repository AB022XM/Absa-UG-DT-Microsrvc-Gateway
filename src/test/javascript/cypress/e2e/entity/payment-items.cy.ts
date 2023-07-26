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

describe('PaymentItems e2e test', () => {
  const paymentItemsPageUrl = '/payment-items';
  const paymentItemsPageUrlPattern = new RegExp('/payment-items(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const paymentItemsSample = { absaTranRef: '037c9e9c-6ba2-46ac-8872-d0c6fba82e76' };

  let paymentItems;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/payment-items+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/payment-items').as('postEntityRequest');
    cy.intercept('DELETE', '/api/payment-items/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (paymentItems) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/payment-items/${paymentItems.id}`,
      }).then(() => {
        paymentItems = undefined;
      });
    }
  });

  it('PaymentItems menu should load PaymentItems page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment-items');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('PaymentItems').should('exist');
    cy.url().should('match', paymentItemsPageUrlPattern);
  });

  describe('PaymentItems page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(paymentItemsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create PaymentItems page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/payment-items/new$'));
        cy.getEntityCreateUpdateHeading('PaymentItems');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentItemsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/payment-items',
          body: paymentItemsSample,
        }).then(({ body }) => {
          paymentItems = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/payment-items+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [paymentItems],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(paymentItemsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details PaymentItems page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('paymentItems');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentItemsPageUrlPattern);
      });

      it('edit button click should load edit PaymentItems page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PaymentItems');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentItemsPageUrlPattern);
      });

      it('edit button click should load edit PaymentItems page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PaymentItems');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentItemsPageUrlPattern);
      });

      it('last delete button click should delete instance of PaymentItems', () => {
        cy.intercept('GET', '/api/payment-items/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('paymentItems').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentItemsPageUrlPattern);

        paymentItems = undefined;
      });
    });
  });

  describe('new PaymentItems page', () => {
    beforeEach(() => {
      cy.visit(`${paymentItemsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('PaymentItems');
    });

    it('should create an instance of PaymentItems', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('a58717bc-4395-4199-8543-69cb85aa2fba')
        .invoke('val')
        .should('match', new RegExp('a58717bc-4395-4199-8543-69cb85aa2fba'));

      cy.get(`[data-cy="recordId"]`).type('exploit Investment').should('have.value', 'exploit Investment');

      cy.get(`[data-cy="productCategoryId"]`).type('52550').should('have.value', '52550');

      cy.get(`[data-cy="billerId"]`).type('33129').should('have.value', '33129');

      cy.get(`[data-cy="paymentItemCode"]`).type('Solutions Secured red').should('have.value', 'Solutions Secured red');

      cy.get(`[data-cy="paymentItemId"]`).type('90743').should('have.value', '90743');

      cy.get(`[data-cy="paymentItemName"]`).type('Account Carolina Garden').should('have.value', 'Account Carolina Garden');

      cy.get(`[data-cy="paymentItemDescription"]`).type('JBOD Courts programming').should('have.value', 'JBOD Courts programming');

      cy.get(`[data-cy="isActive"]`).should('not.be.checked');
      cy.get(`[data-cy="isActive"]`).click().should('be.checked');

      cy.get(`[data-cy="hasFixedPrice"]`).should('not.be.checked');
      cy.get(`[data-cy="hasFixedPrice"]`).click().should('be.checked');

      cy.get(`[data-cy="hasVariablePrice"]`).should('not.be.checked');
      cy.get(`[data-cy="hasVariablePrice"]`).click().should('be.checked');

      cy.get(`[data-cy="hasDiscount"]`).should('not.be.checked');
      cy.get(`[data-cy="hasDiscount"]`).click().should('be.checked');

      cy.get(`[data-cy="hasTax"]`).should('not.be.checked');
      cy.get(`[data-cy="hasTax"]`).click().should('be.checked');

      cy.get(`[data-cy="amount"]`).type('38917').should('have.value', '38917');

      cy.get(`[data-cy="chargeAmount"]`).type('22150').should('have.value', '22150');

      cy.get(`[data-cy="hasChargeAmount"]`).should('not.be.checked');
      cy.get(`[data-cy="hasChargeAmount"]`).click().should('be.checked');

      cy.get(`[data-cy="taxAmount"]`).type('41498').should('have.value', '41498');

      cy.get(`[data-cy="freeText"]`).type('Wooden Unbranded Wooden').should('have.value', 'Wooden Unbranded Wooden');

      cy.get(`[data-cy="freeText1"]`).type('implementation').should('have.value', 'implementation');

      cy.get(`[data-cy="freeText2"]`).type('Concrete card').should('have.value', 'Concrete card');

      cy.get(`[data-cy="freeText3"]`).type('clicks-and-mortar').should('have.value', 'clicks-and-mortar');

      cy.get(`[data-cy="freeText4"]`).type('protocol Music').should('have.value', 'protocol Music');

      cy.get(`[data-cy="freeText5"]`).type('Unbranded port').should('have.value', 'Unbranded port');

      cy.get(`[data-cy="freeText6"]`).type('National Frozen').should('have.value', 'National Frozen');

      cy.get(`[data-cy="freeText7"]`).type('pink integrated Wooden').should('have.value', 'pink integrated Wooden');

      cy.get(`[data-cy="freeText8"]`).type('Developer metrics').should('have.value', 'Developer metrics');

      cy.get(`[data-cy="freeText9"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="freeText10"]`).type('Multi-channelled').should('have.value', 'Multi-channelled');

      cy.get(`[data-cy="freeText11"]`).type('Soap Computers copying').should('have.value', 'Soap Computers copying');

      cy.get(`[data-cy="freeText12"]`).type('Zimbabwe').should('have.value', 'Zimbabwe');

      cy.get(`[data-cy="freeText13"]`).type('Manager').should('have.value', 'Manager');

      cy.get(`[data-cy="freeText14"]`).type('Montana override').should('have.value', 'Montana override');

      cy.get(`[data-cy="freeText15"]`).type('Rand Garden').should('have.value', 'Rand Garden');

      cy.get(`[data-cy="freeText16"]`).type('TCP Operative wireless').should('have.value', 'TCP Operative wireless');

      cy.get(`[data-cy="freeText17"]`).type('parsing').should('have.value', 'parsing');

      cy.get(`[data-cy="freeText18"]`).type('Shoes').should('have.value', 'Shoes');

      cy.get(`[data-cy="freeText19"]`).type('SCSI impactful Ball').should('have.value', 'SCSI impactful Ball');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="status"]`).type('invoice middleware').should('have.value', 'invoice middleware');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T16:58').blur().should('have.value', '2023-07-25T16:58');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T02:23').blur().should('have.value', '2023-07-25T02:23');

      cy.get(`[data-cy="deletedAt"]`).type('2023-07-25T01:25').blur().should('have.value', '2023-07-25T01:25');

      cy.get(`[data-cy="deletedBy"]`).type('Forward CFA navigate').should('have.value', 'Forward CFA navigate');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        paymentItems = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', paymentItemsPageUrlPattern);
    });
  });
});
