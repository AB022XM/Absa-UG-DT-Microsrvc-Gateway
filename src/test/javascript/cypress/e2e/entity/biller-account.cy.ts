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

describe('BillerAccount e2e test', () => {
  const billerAccountPageUrl = '/biller-account';
  const billerAccountPageUrlPattern = new RegExp('/biller-account(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const billerAccountSample = {
    recordId: 'Digitized virtual',
    billerId: 'synthesizing Grass-roots deliver',
    billerName: 'Soft scalable neural',
    billerAccNumber: 'Account Strategist Account',
    createdAt: '2023-07-24T23:24:09.266Z',
  };

  let billerAccount;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/biller-accounts+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/biller-accounts').as('postEntityRequest');
    cy.intercept('DELETE', '/api/biller-accounts/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (billerAccount) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/biller-accounts/${billerAccount.id}`,
      }).then(() => {
        billerAccount = undefined;
      });
    }
  });

  it('BillerAccounts menu should load BillerAccounts page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('biller-account');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('BillerAccount').should('exist');
    cy.url().should('match', billerAccountPageUrlPattern);
  });

  describe('BillerAccount page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(billerAccountPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create BillerAccount page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/biller-account/new$'));
        cy.getEntityCreateUpdateHeading('BillerAccount');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerAccountPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/biller-accounts',
          body: billerAccountSample,
        }).then(({ body }) => {
          billerAccount = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/biller-accounts+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [billerAccount],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(billerAccountPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details BillerAccount page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('billerAccount');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerAccountPageUrlPattern);
      });

      it('edit button click should load edit BillerAccount page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('BillerAccount');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerAccountPageUrlPattern);
      });

      it('edit button click should load edit BillerAccount page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('BillerAccount');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerAccountPageUrlPattern);
      });

      it('last delete button click should delete instance of BillerAccount', () => {
        cy.intercept('GET', '/api/biller-accounts/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('billerAccount').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerAccountPageUrlPattern);

        billerAccount = undefined;
      });
    });
  });

  describe('new BillerAccount page', () => {
    beforeEach(() => {
      cy.visit(`${billerAccountPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('BillerAccount');
    });

    it('should create an instance of BillerAccount', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('a2fcaa98-9b6b-465c-8604-23cd52cd9932')
        .invoke('val')
        .should('match', new RegExp('a2fcaa98-9b6b-465c-8604-23cd52cd9932'));

      cy.get(`[data-cy="recordId"]`).type('orchid').should('have.value', 'orchid');

      cy.get(`[data-cy="billerId"]`).type('Fresh Bedfordshire').should('have.value', 'Fresh Bedfordshire');

      cy.get(`[data-cy="billerName"]`).type('Synergized Central Streamlined').should('have.value', 'Synergized Central Streamlined');

      cy.get(`[data-cy="billerAccNumber"]`).type('Global Wooden ivory').should('have.value', 'Global Wooden ivory');

      cy.get(`[data-cy="billerAccountDescription"]`).type('Gorgeous').should('have.value', 'Gorgeous');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T14:41').blur().should('have.value', '2023-07-25T14:41');

      cy.get(`[data-cy="billerFreeField1"]`).type('Diverse Metal').should('have.value', 'Diverse Metal');

      cy.get(`[data-cy="billerFreeField2"]`).type('Garden').should('have.value', 'Garden');

      cy.get(`[data-cy="billerFreeField3"]`).type('blue Chad Ecuador').should('have.value', 'blue Chad Ecuador');

      cy.get(`[data-cy="billerFreeField4"]`).type('Soft Nebraska Forward').should('have.value', 'Soft Nebraska Forward');

      cy.get(`[data-cy="billerFreeField5"]`).type('Neck Automotive').should('have.value', 'Neck Automotive');

      cy.get(`[data-cy="billerFreeField6"]`).type('Glen Persistent open-source').should('have.value', 'Glen Persistent open-source');

      cy.get(`[data-cy="billerFreeField7"]`).type('quantify').should('have.value', 'quantify');

      cy.get(`[data-cy="billerFreeField8"]`).type('withdrawal').should('have.value', 'withdrawal');

      cy.get(`[data-cy="billerFreeField9"]`).type('Steel orange Estates').should('have.value', 'Steel orange Estates');

      cy.get(`[data-cy="billerFreeField10"]`).type('users B2C Kansas').should('have.value', 'users B2C Kansas');

      cy.get(`[data-cy="billerFreeField11"]`).type('Specialist Generic').should('have.value', 'Specialist Generic');

      cy.get(`[data-cy="billerFreeField12"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="billerFreeField13"]`).type('Diverse').should('have.value', 'Diverse');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T12:02').blur().should('have.value', '2023-07-25T12:02');

      cy.get(`[data-cy="createdBy"]`).type('clear-thinking Executive Director').should('have.value', 'clear-thinking Executive Director');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T06:06').blur().should('have.value', '2023-07-25T06:06');

      cy.get(`[data-cy="updatedBy"]`).type('Generic Data').should('have.value', 'Generic Data');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        billerAccount = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', billerAccountPageUrlPattern);
    });
  });
});
