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

describe('Currency e2e test', () => {
  const currencyPageUrl = '/currency';
  const currencyPageUrlPattern = new RegExp('/currency(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const currencySample = { timestamp: '2023-07-25T04:46:46.136Z', createdAt: '2023-07-24T23:05:15.711Z' };

  let currency;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/currencies+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/currencies').as('postEntityRequest');
    cy.intercept('DELETE', '/api/currencies/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (currency) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/currencies/${currency.id}`,
      }).then(() => {
        currency = undefined;
      });
    }
  });

  it('Currencies menu should load Currencies page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('currency');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Currency').should('exist');
    cy.url().should('match', currencyPageUrlPattern);
  });

  describe('Currency page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(currencyPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Currency page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/currency/new$'));
        cy.getEntityCreateUpdateHeading('Currency');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', currencyPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/currencies',
          body: currencySample,
        }).then(({ body }) => {
          currency = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/currencies+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [currency],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(currencyPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Currency page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('currency');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', currencyPageUrlPattern);
      });

      it('edit button click should load edit Currency page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Currency');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', currencyPageUrlPattern);
      });

      it('edit button click should load edit Currency page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Currency');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', currencyPageUrlPattern);
      });

      it('last delete button click should delete instance of Currency', () => {
        cy.intercept('GET', '/api/currencies/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('currency').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', currencyPageUrlPattern);

        currency = undefined;
      });
    });
  });

  describe('new Currency page', () => {
    beforeEach(() => {
      cy.visit(`${currencyPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Currency');
    });

    it('should create an instance of Currency', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('47e063e9-cf1a-46ae-bfb7-388b7c282650')
        .invoke('val')
        .should('match', new RegExp('47e063e9-cf1a-46ae-bfb7-388b7c282650'));

      cy.get(`[data-cy="dtDTransactionId"]`).type('Checking Row Creative').should('have.value', 'Checking Row Creative');

      cy.get(`[data-cy="amolDTransactionId"]`).type('Shoal Nicaragua').should('have.value', 'Shoal Nicaragua');

      cy.get(`[data-cy="transactionReferenceNumber"]`)
        .type('conglomeration Home driver')
        .should('have.value', 'conglomeration Home driver');

      cy.get(`[data-cy="token"]`).type('Technician capacitor firewall').should('have.value', 'Technician capacitor firewall');

      cy.get(`[data-cy="currencyUniqueId"]`).type('Maryland').should('have.value', 'Maryland');

      cy.get(`[data-cy="currencyCode"]`).type('KWD').should('have.value', 'KWD');

      cy.get(`[data-cy="currencyName"]`).type('Afghani').should('have.value', 'Afghani');

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField2"]`).type('microchip').should('have.value', 'microchip');

      cy.get(`[data-cy="freeField3"]`).type('Jamaica').should('have.value', 'Jamaica');

      cy.get(`[data-cy="freeField4"]`).type('object-oriented Bike even-keeled').should('have.value', 'object-oriented Bike even-keeled');

      cy.get(`[data-cy="freeField5"]`).type('US').should('have.value', 'US');

      cy.get(`[data-cy="freeField6"]`).type('models EXE').should('have.value', 'models EXE');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T03:51').blur().should('have.value', '2023-07-25T03:51');

      cy.get(`[data-cy="recordStatus"]`).select('PENDING');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T01:08').blur().should('have.value', '2023-07-25T01:08');

      cy.get(`[data-cy="createdBy"]`).type('Finland Oval').should('have.value', 'Finland Oval');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T02:49').blur().should('have.value', '2023-07-25T02:49');

      cy.get(`[data-cy="updatedBy"]`).type('transform EXE').should('have.value', 'transform EXE');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        currency = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', currencyPageUrlPattern);
    });
  });
});
