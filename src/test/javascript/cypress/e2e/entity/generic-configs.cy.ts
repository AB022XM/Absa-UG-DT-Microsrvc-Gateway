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

describe('GenericConfigs e2e test', () => {
  const genericConfigsPageUrl = '/generic-configs';
  const genericConfigsPageUrlPattern = new RegExp('/generic-configs(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const genericConfigsSample = { timestamp: '2023-07-25T10:57:45.003Z' };

  let genericConfigs;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/generic-configs+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/generic-configs').as('postEntityRequest');
    cy.intercept('DELETE', '/api/generic-configs/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (genericConfigs) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/generic-configs/${genericConfigs.id}`,
      }).then(() => {
        genericConfigs = undefined;
      });
    }
  });

  it('GenericConfigs menu should load GenericConfigs page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('generic-configs');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('GenericConfigs').should('exist');
    cy.url().should('match', genericConfigsPageUrlPattern);
  });

  describe('GenericConfigs page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(genericConfigsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create GenericConfigs page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/generic-configs/new$'));
        cy.getEntityCreateUpdateHeading('GenericConfigs');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', genericConfigsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/generic-configs',
          body: genericConfigsSample,
        }).then(({ body }) => {
          genericConfigs = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/generic-configs+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [genericConfigs],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(genericConfigsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details GenericConfigs page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('genericConfigs');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', genericConfigsPageUrlPattern);
      });

      it('edit button click should load edit GenericConfigs page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('GenericConfigs');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', genericConfigsPageUrlPattern);
      });

      it('edit button click should load edit GenericConfigs page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('GenericConfigs');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', genericConfigsPageUrlPattern);
      });

      it('last delete button click should delete instance of GenericConfigs', () => {
        cy.intercept('GET', '/api/generic-configs/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('genericConfigs').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', genericConfigsPageUrlPattern);

        genericConfigs = undefined;
      });
    });
  });

  describe('new GenericConfigs page', () => {
    beforeEach(() => {
      cy.visit(`${genericConfigsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('GenericConfigs');
    });

    it('should create an instance of GenericConfigs', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('342b1132-757c-4536-84f7-d7ba6e673f9c')
        .invoke('val')
        .should('match', new RegExp('342b1132-757c-4536-84f7-d7ba6e673f9c'));

      cy.get(`[data-cy="recordId"]`).type('North TCP').should('have.value', 'North TCP');

      cy.get(`[data-cy="configId"]`).type('hack Ball ubiquitous').should('have.value', 'hack Ball ubiquitous');

      cy.get(`[data-cy="configName"]`).type('Plains Vatu users').should('have.value', 'Plains Vatu users');

      cy.get(`[data-cy="configsDetails"]`).type('Branding invoice Steel').should('have.value', 'Branding invoice Steel');

      cy.get(`[data-cy="additionalConfigs"]`).type('intuitive Peso').should('have.value', 'intuitive Peso');

      cy.get(`[data-cy="freeField"]`).should('not.be.checked');
      cy.get(`[data-cy="freeField"]`).click().should('be.checked');

      cy.get(`[data-cy="freeField1"]`).should('not.be.checked');
      cy.get(`[data-cy="freeField1"]`).click().should('be.checked');

      cy.get(`[data-cy="freeField2"]`).type('parse').should('have.value', 'parse');

      cy.get(`[data-cy="freeField3"]`).type('program payment').should('have.value', 'program payment');

      cy.get(`[data-cy="freeField4"]`).type('index').should('have.value', 'index');

      cy.get(`[data-cy="freeField5"]`).type('Agent Squares').should('have.value', 'Agent Squares');

      cy.get(`[data-cy="freeField6"]`).type('Netherlands Borders parsing').should('have.value', 'Netherlands Borders parsing');

      cy.get(`[data-cy="freeField8"]`).type('Buckinghamshire').should('have.value', 'Buckinghamshire');

      cy.get(`[data-cy="freeField9"]`).type('schemas').should('have.value', 'schemas');

      cy.get(`[data-cy="freeField10"]`).type('Data Nebraska Alabama').should('have.value', 'Data Nebraska Alabama');

      cy.get(`[data-cy="freeField11"]`).type('array Associate Corporate').should('have.value', 'array Associate Corporate');

      cy.get(`[data-cy="freeField12"]`).type('Savings Industrial').should('have.value', 'Savings Industrial');

      cy.get(`[data-cy="freeField13"]`).type('transmit Sports').should('have.value', 'transmit Sports');

      cy.get(`[data-cy="freeField14"]`).type('back').should('have.value', 'back');

      cy.setFieldImageAsBytesOfEntity('freeField15', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField16"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField17"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField18', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField19"]`).type('Beauty infrastructures').should('have.value', 'Beauty infrastructures');

      cy.get(`[data-cy="freeField20"]`).type('Shores integrated').should('have.value', 'Shores integrated');

      cy.get(`[data-cy="freeField21"]`).type('indexing Books Small').should('have.value', 'indexing Books Small');

      cy.get(`[data-cy="freeField22"]`).type('Loan Minnesota Ethiopia').should('have.value', 'Loan Minnesota Ethiopia');

      cy.get(`[data-cy="freeField23"]`).type('deliverables matrix Berkshire').should('have.value', 'deliverables matrix Berkshire');

      cy.get(`[data-cy="freeField24"]`).type('cross-platform Devolved').should('have.value', 'cross-platform Devolved');

      cy.setFieldImageAsBytesOfEntity('freeField25', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField26"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField27"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.setFieldImageAsBytesOfEntity('freeField28', 'integration-test.png', 'image/png');

      cy.get(`[data-cy="freeField29"]`).type('Steel Account').should('have.value', 'Steel Account');

      cy.get(`[data-cy="freeField30"]`).type('Dakota Security Automotive').should('have.value', 'Dakota Security Automotive');

      cy.get(`[data-cy="freeField31"]`).type('Interactions Virginia Liberian').should('have.value', 'Interactions Virginia Liberian');

      cy.get(`[data-cy="freeField32"]`).type('Ball Guinea static').should('have.value', 'Ball Guinea static');

      cy.get(`[data-cy="freeField33"]`).type('Cove Guinea-Bissau').should('have.value', 'Cove Guinea-Bissau');

      cy.get(`[data-cy="freeField34"]`).type('Wisconsin').should('have.value', 'Wisconsin');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T14:02').blur().should('have.value', '2023-07-25T14:02');

      cy.get(`[data-cy="recordStatus"]`).select('INITIATED');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        genericConfigs = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', genericConfigsPageUrlPattern);
    });
  });
});
