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

describe('DefaultSettings e2e test', () => {
  const defaultSettingsPageUrl = '/default-settings';
  const defaultSettingsPageUrlPattern = new RegExp('/default-settings(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const defaultSettingsSample = { absaTranRef: '9dcd5988-9584-42a3-ad9b-23a40d90998f' };

  let defaultSettings;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/default-settings+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/default-settings').as('postEntityRequest');
    cy.intercept('DELETE', '/api/default-settings/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (defaultSettings) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/default-settings/${defaultSettings.id}`,
      }).then(() => {
        defaultSettings = undefined;
      });
    }
  });

  it('DefaultSettings menu should load DefaultSettings page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('default-settings');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DefaultSettings').should('exist');
    cy.url().should('match', defaultSettingsPageUrlPattern);
  });

  describe('DefaultSettings page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(defaultSettingsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DefaultSettings page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/default-settings/new$'));
        cy.getEntityCreateUpdateHeading('DefaultSettings');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', defaultSettingsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/default-settings',
          body: defaultSettingsSample,
        }).then(({ body }) => {
          defaultSettings = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/default-settings+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [defaultSettings],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(defaultSettingsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DefaultSettings page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('defaultSettings');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', defaultSettingsPageUrlPattern);
      });

      it('edit button click should load edit DefaultSettings page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DefaultSettings');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', defaultSettingsPageUrlPattern);
      });

      it('edit button click should load edit DefaultSettings page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DefaultSettings');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', defaultSettingsPageUrlPattern);
      });

      it('last delete button click should delete instance of DefaultSettings', () => {
        cy.intercept('GET', '/api/default-settings/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('defaultSettings').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', defaultSettingsPageUrlPattern);

        defaultSettings = undefined;
      });
    });
  });

  describe('new DefaultSettings page', () => {
    beforeEach(() => {
      cy.visit(`${defaultSettingsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DefaultSettings');
    });

    it('should create an instance of DefaultSettings', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('173aaf15-1675-4259-98f2-9420299ff5cd')
        .invoke('val')
        .should('match', new RegExp('173aaf15-1675-4259-98f2-9420299ff5cd'));

      cy.get(`[data-cy="dtDTransactionId"]`).type('Rustic').should('have.value', 'Rustic');

      cy.get(`[data-cy="amolDTransactionId"]`).type('Chair FTP Chilean').should('have.value', 'Chair FTP Chilean');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Borders').should('have.value', 'Borders');

      cy.get(`[data-cy="token"]`).type('Coordinator').should('have.value', 'Coordinator');

      cy.get(`[data-cy="thirdPartyDTransactionId"]`).type('indigo synthesizing').should('have.value', 'indigo synthesizing');

      cy.get(`[data-cy="defaultSettingCode"]`).type('Checking Grove').should('have.value', 'Checking Grove');

      cy.get(`[data-cy="jsonSettings"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="appConfig"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="appName"]`).type('Security Generic Mall').should('have.value', 'Security Generic Mall');

      cy.get(`[data-cy="freeField"]`).type('sky navigate').should('have.value', 'sky navigate');

      cy.get(`[data-cy="freeField1"]`).type('real-time AI').should('have.value', 'real-time AI');

      cy.get(`[data-cy="freeField2"]`).type('PNG').should('have.value', 'PNG');

      cy.get(`[data-cy="freeField3"]`).type('compress Nevada').should('have.value', 'compress Nevada');

      cy.get(`[data-cy="freeField4"]`).type('deposit').should('have.value', 'deposit');

      cy.get(`[data-cy="freeField5"]`).type('Unbranded Bahraini THX').should('have.value', 'Unbranded Bahraini THX');

      cy.get(`[data-cy="freeField6"]`).type('Shoes Indiana').should('have.value', 'Shoes Indiana');

      cy.get(`[data-cy="freeField8"]`).type('RAM extend').should('have.value', 'RAM extend');

      cy.get(`[data-cy="freeField9"]`).type('green').should('have.value', 'green');

      cy.get(`[data-cy="freeField10"]`).type('architect and').should('have.value', 'architect and');

      cy.get(`[data-cy="freeField11"]`).type('parsing Credit indigo').should('have.value', 'parsing Credit indigo');

      cy.get(`[data-cy="freeField12"]`).type('bypass iterate').should('have.value', 'bypass iterate');

      cy.get(`[data-cy="freeField13"]`).type('bandwidth').should('have.value', 'bandwidth');

      cy.get(`[data-cy="freeField14"]`).type('quantifying').should('have.value', 'quantifying');

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

      cy.get(`[data-cy="freeField19"]`).type('Toys').should('have.value', 'Toys');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T18:26').blur().should('have.value', '2023-07-25T18:26');

      cy.get(`[data-cy="recordStatus"]`).select('APPROVED');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T01:12').blur().should('have.value', '2023-07-25T01:12');

      cy.get(`[data-cy="createdBy"]`).type('synergize bypassing').should('have.value', 'synergize bypassing');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T20:50').blur().should('have.value', '2023-07-25T20:50');

      cy.get(`[data-cy="updatedby"]`).type('lime mesh').should('have.value', 'lime mesh');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        defaultSettings = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', defaultSettingsPageUrlPattern);
    });
  });
});
