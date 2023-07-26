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

describe('Apps e2e test', () => {
  const appsPageUrl = '/apps';
  const appsPageUrlPattern = new RegExp('/apps(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const appsSample = { appId: 'Specialist', appCurrentVersion: 'scalable Dale', appName: 'solutions' };

  let apps;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/apps+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/apps').as('postEntityRequest');
    cy.intercept('DELETE', '/api/apps/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (apps) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/apps/${apps.id}`,
      }).then(() => {
        apps = undefined;
      });
    }
  });

  it('Apps menu should load Apps page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('apps');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Apps').should('exist');
    cy.url().should('match', appsPageUrlPattern);
  });

  describe('Apps page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(appsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Apps page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/apps/new$'));
        cy.getEntityCreateUpdateHeading('Apps');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/apps',
          body: appsSample,
        }).then(({ body }) => {
          apps = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/apps+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [apps],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(appsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Apps page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('apps');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appsPageUrlPattern);
      });

      it('edit button click should load edit Apps page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Apps');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appsPageUrlPattern);
      });

      it('edit button click should load edit Apps page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Apps');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appsPageUrlPattern);
      });

      it('last delete button click should delete instance of Apps', () => {
        cy.intercept('GET', '/api/apps/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('apps').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appsPageUrlPattern);

        apps = undefined;
      });
    });
  });

  describe('new Apps page', () => {
    beforeEach(() => {
      cy.visit(`${appsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Apps');
    });

    it('should create an instance of Apps', () => {
      cy.get(`[data-cy="recordId"]`).type('Avon').should('have.value', 'Avon');

      cy.get(`[data-cy="configId"]`).type('encoding deposit Associate').should('have.value', 'encoding deposit Associate');

      cy.get(`[data-cy="appId"]`).type('Sleek').should('have.value', 'Sleek');

      cy.get(`[data-cy="appCurrentVersion"]`).type('bypassing Senior Springs').should('have.value', 'bypassing Senior Springs');

      cy.get(`[data-cy="appName"]`).type('Customer-focused Wisconsin').should('have.value', 'Customer-focused Wisconsin');

      cy.get(`[data-cy="appDescription"]`).type('open-source').should('have.value', 'open-source');

      cy.get(`[data-cy="appStatus"]`).type('pink').should('have.value', 'pink');

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField2"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField3"]`).type('Handcrafted').should('have.value', 'Handcrafted');

      cy.get(`[data-cy="freeField4"]`).type('Advanced haptic').should('have.value', 'Advanced haptic');

      cy.get(`[data-cy="freeField5"]`)
        .type('value-added Implementation Concrete')
        .should('have.value', 'value-added Implementation Concrete');

      cy.get(`[data-cy="freeField6"]`).type('generating uniform').should('have.value', 'generating uniform');

      cy.get(`[data-cy="freeField7"]`).type('Marshall Implementation Corporate').should('have.value', 'Marshall Implementation Corporate');

      cy.get(`[data-cy="freeField8"]`).type('Rand Practical Account').should('have.value', 'Rand Practical Account');

      cy.get(`[data-cy="freeField9"]`).type('index evolve one-to-one').should('have.value', 'index evolve one-to-one');

      cy.get(`[data-cy="freeField10"]`).type('Gloves mesh').should('have.value', 'Gloves mesh');

      cy.get(`[data-cy="freeField11"]`).type('Salad Visionary').should('have.value', 'Salad Visionary');

      cy.get(`[data-cy="freeField12"]`).type('policy').should('have.value', 'policy');

      cy.get(`[data-cy="freeField13"]`)
        .type('Bedfordshire Overpass Buckinghamshire')
        .should('have.value', 'Bedfordshire Overpass Buckinghamshire');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T14:07').blur().should('have.value', '2023-07-25T14:07');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T02:04').blur().should('have.value', '2023-07-25T02:04');

      cy.get(`[data-cy="createdBy"]`).type('Cambridgeshire Profit-focused and').should('have.value', 'Cambridgeshire Profit-focused and');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T11:44').blur().should('have.value', '2023-07-25T11:44');

      cy.get(`[data-cy="updatedby"]`).type('Table Product Nebraska').should('have.value', 'Table Product Nebraska');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        apps = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', appsPageUrlPattern);
    });
  });
});
