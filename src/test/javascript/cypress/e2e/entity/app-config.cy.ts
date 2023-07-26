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

describe('AppConfig e2e test', () => {
  const appConfigPageUrl = '/app-config';
  const appConfigPageUrlPattern = new RegExp('/app-config(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const appConfigSample = {
    configName: 'Mouse invoice',
    configValue: 'Li4vZmFrZS1kYXRhL2Jsb2IvaGlwc3Rlci50eHQ=',
    configType: 'Cambridgeshire',
  };

  let appConfig;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/app-configs+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/app-configs').as('postEntityRequest');
    cy.intercept('DELETE', '/api/app-configs/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (appConfig) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/app-configs/${appConfig.id}`,
      }).then(() => {
        appConfig = undefined;
      });
    }
  });

  it('AppConfigs menu should load AppConfigs page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('app-config');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AppConfig').should('exist');
    cy.url().should('match', appConfigPageUrlPattern);
  });

  describe('AppConfig page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(appConfigPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create AppConfig page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/app-config/new$'));
        cy.getEntityCreateUpdateHeading('AppConfig');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appConfigPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/app-configs',
          body: appConfigSample,
        }).then(({ body }) => {
          appConfig = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/app-configs+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [appConfig],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(appConfigPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details AppConfig page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('appConfig');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appConfigPageUrlPattern);
      });

      it('edit button click should load edit AppConfig page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AppConfig');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appConfigPageUrlPattern);
      });

      it('edit button click should load edit AppConfig page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('AppConfig');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appConfigPageUrlPattern);
      });

      it('last delete button click should delete instance of AppConfig', () => {
        cy.intercept('GET', '/api/app-configs/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('appConfig').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', appConfigPageUrlPattern);

        appConfig = undefined;
      });
    });
  });

  describe('new AppConfig page', () => {
    beforeEach(() => {
      cy.visit(`${appConfigPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('AppConfig');
    });

    it('should create an instance of AppConfig', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('ad806372-d146-404a-bb56-4f3d227b06a5')
        .invoke('val')
        .should('match', new RegExp('ad806372-d146-404a-bb56-4f3d227b06a5'));

      cy.get(`[data-cy="recordId"]`)
        .type('bleeding-edge quantifying Streamlined')
        .should('have.value', 'bleeding-edge quantifying Streamlined');

      cy.get(`[data-cy="configId"]`).type('Account').should('have.value', 'Account');

      cy.get(`[data-cy="configName"]`).type('primary').should('have.value', 'primary');

      cy.get(`[data-cy="configValue"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="configType"]`).type('Jewelery').should('have.value', 'Jewelery');

      cy.get(`[data-cy="configDescription"]`).type('Soft').should('have.value', 'Soft');

      cy.get(`[data-cy="configStatus"]`).type('Fresh virtual hard').should('have.value', 'Fresh virtual hard');

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField2"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField3"]`).type('system').should('have.value', 'system');

      cy.get(`[data-cy="freeField4"]`).type('Unbranded payment Arizona').should('have.value', 'Unbranded payment Arizona');

      cy.get(`[data-cy="freeField5"]`).type('Avon Nepalese').should('have.value', 'Avon Nepalese');

      cy.get(`[data-cy="freeField6"]`).type('vortals Hat').should('have.value', 'vortals Hat');

      cy.get(`[data-cy="freeField7"]`).type('cross-platform ability').should('have.value', 'cross-platform ability');

      cy.get(`[data-cy="freeField8"]`).type('primary Bedfordshire hack').should('have.value', 'primary Bedfordshire hack');

      cy.get(`[data-cy="freeField9"]`).type('syndicate Marketing Lead').should('have.value', 'syndicate Marketing Lead');

      cy.get(`[data-cy="freeField10"]`).type('Keyboard').should('have.value', 'Keyboard');

      cy.get(`[data-cy="freeField11"]`).type('tangible').should('have.value', 'tangible');

      cy.get(`[data-cy="freeField12"]`).type('revolutionary Iowa Metal').should('have.value', 'revolutionary Iowa Metal');

      cy.get(`[data-cy="freeField13"]`).type('Licensed').should('have.value', 'Licensed');

      cy.setFieldImageAsBytesOfEntity('freeField14', 'integration-test.png', 'image/png');

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

      cy.get(`[data-cy="freeField19"]`).type('Manor').should('have.value', 'Manor');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T09:37').blur().should('have.value', '2023-07-25T09:37');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T18:50').blur().should('have.value', '2023-07-25T18:50');

      cy.get(`[data-cy="createdBy"]`).type('Pizza Rand').should('have.value', 'Pizza Rand');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T19:29').blur().should('have.value', '2023-07-25T19:29');

      cy.get(`[data-cy="updatedBy"]`).type('scalable auxiliary Gorgeous').should('have.value', 'scalable auxiliary Gorgeous');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        appConfig = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', appConfigPageUrlPattern);
    });
  });
});
