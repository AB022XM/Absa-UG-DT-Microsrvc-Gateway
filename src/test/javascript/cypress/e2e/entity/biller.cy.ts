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

describe('Biller e2e test', () => {
  const billerPageUrl = '/biller';
  const billerPageUrlPattern = new RegExp('/biller(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const billerSample = {
    billerId: 'Internal modular Connecticut',
    billerCode: 'Kentucky e-tailers',
    createdAt: '2023-07-25T06:41:57.753Z',
  };

  let biller;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/billers+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/billers').as('postEntityRequest');
    cy.intercept('DELETE', '/api/billers/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (biller) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/billers/${biller.id}`,
      }).then(() => {
        biller = undefined;
      });
    }
  });

  it('Billers menu should load Billers page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('biller');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Biller').should('exist');
    cy.url().should('match', billerPageUrlPattern);
  });

  describe('Biller page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(billerPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Biller page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/biller/new$'));
        cy.getEntityCreateUpdateHeading('Biller');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/billers',
          body: billerSample,
        }).then(({ body }) => {
          biller = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/billers+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [biller],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(billerPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Biller page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('biller');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerPageUrlPattern);
      });

      it('edit button click should load edit Biller page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Biller');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerPageUrlPattern);
      });

      it('edit button click should load edit Biller page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Biller');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerPageUrlPattern);
      });

      it('last delete button click should delete instance of Biller', () => {
        cy.intercept('GET', '/api/billers/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('biller').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', billerPageUrlPattern);

        biller = undefined;
      });
    });
  });

  describe('new Biller page', () => {
    beforeEach(() => {
      cy.visit(`${billerPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Biller');
    });

    it('should create an instance of Biller', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('929908f7-20c0-426a-bab1-8e99766751e0')
        .invoke('val')
        .should('match', new RegExp('929908f7-20c0-426a-bab1-8e99766751e0'));

      cy.get(`[data-cy="billerId"]`).type('SMTP').should('have.value', 'SMTP');

      cy.get(`[data-cy="billerCode"]`).type('Tasty Concrete').should('have.value', 'Tasty Concrete');

      cy.get(`[data-cy="billerName"]`).type('virtual interfaces Indonesia').should('have.value', 'virtual interfaces Indonesia');

      cy.get(`[data-cy="billerCategoryId"]`).type('Rubber TCP Fish').should('have.value', 'Rubber TCP Fish');

      cy.get(`[data-cy="addressId"]`).type('Oro Upgradable').should('have.value', 'Oro Upgradable');

      cy.get(`[data-cy="narative"]`).type('Corporate Front-line Face').should('have.value', 'Corporate Front-line Face');

      cy.get(`[data-cy="narative1"]`).type('incremental Manager lime').should('have.value', 'incremental Manager lime');

      cy.get(`[data-cy="narative2"]`).type('quantify').should('have.value', 'quantify');

      cy.get(`[data-cy="narative3"]`).type('portal maximize').should('have.value', 'portal maximize');

      cy.get(`[data-cy="narative4"]`).type('Cambridgeshire').should('have.value', 'Cambridgeshire');

      cy.get(`[data-cy="narative5"]`).type('infomediaries').should('have.value', 'infomediaries');

      cy.get(`[data-cy="narative6"]`).type('Guiana Toys').should('have.value', 'Guiana Toys');

      cy.get(`[data-cy="narative7"]`).type('azure Plastic purple').should('have.value', 'azure Plastic purple');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T07:42').blur().should('have.value', '2023-07-25T07:42');

      cy.get(`[data-cy="freeField1"]`).type('payment').should('have.value', 'payment');

      cy.get(`[data-cy="freeField2"]`).type('bleeding-edge Up-sized deposit').should('have.value', 'bleeding-edge Up-sized deposit');

      cy.get(`[data-cy="freeField3"]`).type('Home paradigm').should('have.value', 'Home paradigm');

      cy.get(`[data-cy="freeField4"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="freeField5"]`).type('matrices Pakistan collaborative').should('have.value', 'matrices Pakistan collaborative');

      cy.get(`[data-cy="freeField6"]`).type('exploit framework Polarised').should('have.value', 'exploit framework Polarised');

      cy.get(`[data-cy="freeField7"]`).type('Jewelery').should('have.value', 'Jewelery');

      cy.get(`[data-cy="freeField8"]`).type('Mexico').should('have.value', 'Mexico');

      cy.get(`[data-cy="freeField9"]`).type('RAM').should('have.value', 'RAM');

      cy.get(`[data-cy="freeField10"]`).type('envisioneer Wooden Grocery').should('have.value', 'envisioneer Wooden Grocery');

      cy.get(`[data-cy="freeField11"]`).type('Central Chair').should('have.value', 'Central Chair');

      cy.get(`[data-cy="freeField12"]`).type('FTP enable').should('have.value', 'FTP enable');

      cy.get(`[data-cy="freeField13"]`).type('Republic Bedfordshire Plastic').should('have.value', 'Republic Bedfordshire Plastic');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T19:36').blur().should('have.value', '2023-07-25T19:36');

      cy.get(`[data-cy="createdBy"]`).type('calculate Berkshire payment').should('have.value', 'calculate Berkshire payment');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T19:48').blur().should('have.value', '2023-07-25T19:48');

      cy.get(`[data-cy="updatedBy"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        biller = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', billerPageUrlPattern);
    });
  });
});
