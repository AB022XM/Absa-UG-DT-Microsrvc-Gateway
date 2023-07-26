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

describe('PaymentConfig e2e test', () => {
  const paymentConfigPageUrl = '/payment-config';
  const paymentConfigPageUrlPattern = new RegExp('/payment-config(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const paymentConfigSample = { paymentName: 'success', paymentType: 'National Ball open', timestamp: '2023-07-25T04:09:08.346Z' };

  let paymentConfig;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/payment-configs+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/payment-configs').as('postEntityRequest');
    cy.intercept('DELETE', '/api/payment-configs/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (paymentConfig) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/payment-configs/${paymentConfig.id}`,
      }).then(() => {
        paymentConfig = undefined;
      });
    }
  });

  it('PaymentConfigs menu should load PaymentConfigs page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment-config');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('PaymentConfig').should('exist');
    cy.url().should('match', paymentConfigPageUrlPattern);
  });

  describe('PaymentConfig page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(paymentConfigPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create PaymentConfig page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/payment-config/new$'));
        cy.getEntityCreateUpdateHeading('PaymentConfig');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentConfigPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/payment-configs',
          body: paymentConfigSample,
        }).then(({ body }) => {
          paymentConfig = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/payment-configs+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [paymentConfig],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(paymentConfigPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details PaymentConfig page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('paymentConfig');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentConfigPageUrlPattern);
      });

      it('edit button click should load edit PaymentConfig page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PaymentConfig');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentConfigPageUrlPattern);
      });

      it('edit button click should load edit PaymentConfig page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PaymentConfig');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentConfigPageUrlPattern);
      });

      it('last delete button click should delete instance of PaymentConfig', () => {
        cy.intercept('GET', '/api/payment-configs/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('paymentConfig').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', paymentConfigPageUrlPattern);

        paymentConfig = undefined;
      });
    });
  });

  describe('new PaymentConfig page', () => {
    beforeEach(() => {
      cy.visit(`${paymentConfigPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('PaymentConfig');
    });

    it('should create an instance of PaymentConfig', () => {
      cy.get(`[data-cy="recordId"]`).type('Unbranded Home').should('have.value', 'Unbranded Home');

      cy.get(`[data-cy="paymentId"]`).type('Incredible SMTP').should('have.value', 'Incredible SMTP');

      cy.get(`[data-cy="paymentName"]`).type('Algeria').should('have.value', 'Algeria');

      cy.get(`[data-cy="paymentType"]`).type('generating').should('have.value', 'generating');

      cy.get(`[data-cy="paymentDescription"]`).type('Creative driver transition').should('have.value', 'Creative driver transition');

      cy.get(`[data-cy="paymentStatus"]`).type('Computer').should('have.value', 'Computer');

      cy.get(`[data-cy="paymentMethod"]`).select('BANK_TRANSFER');

      cy.get(`[data-cy="paymentAmount"]`).type('47280').should('have.value', '47280');

      cy.get(`[data-cy="additionalConfig"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField1"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField2"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="freeField3"]`).type('Parks Kentucky').should('have.value', 'Parks Kentucky');

      cy.get(`[data-cy="freeField4"]`).type('grey Small Applications').should('have.value', 'grey Small Applications');

      cy.get(`[data-cy="freeField5"]`).type('SMS Forward').should('have.value', 'SMS Forward');

      cy.get(`[data-cy="freeField6"]`).type('Loan Gabon').should('have.value', 'Loan Gabon');

      cy.get(`[data-cy="freeField8"]`).type('SDD Orchestrator Jersey').should('have.value', 'SDD Orchestrator Jersey');

      cy.get(`[data-cy="freeField9"]`).type('robust Montana').should('have.value', 'robust Montana');

      cy.get(`[data-cy="freeField10"]`).type('Azerbaijan').should('have.value', 'Azerbaijan');

      cy.get(`[data-cy="freeField11"]`).type('Pakistan Focused').should('have.value', 'Pakistan Focused');

      cy.get(`[data-cy="freeField12"]`)
        .type('Future cross-platform content-based')
        .should('have.value', 'Future cross-platform content-based');

      cy.get(`[data-cy="freeField13"]`)
        .type('Wisconsin compressing web-readiness')
        .should('have.value', 'Wisconsin compressing web-readiness');

      cy.get(`[data-cy="freeField14"]`)
        .type('context-sensitive whiteboard Metal')
        .should('have.value', 'context-sensitive whiteboard Metal');

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

      cy.get(`[data-cy="freeField19"]`).type('e-business global').should('have.value', 'e-business global');

      cy.get(`[data-cy="freeField20"]`).type('Drive').should('have.value', 'Drive');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T07:24').blur().should('have.value', '2023-07-25T07:24');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T19:31').blur().should('have.value', '2023-07-25T19:31');

      cy.get(`[data-cy="createdBy"]`).type('Kuwait value-added Wyoming').should('have.value', 'Kuwait value-added Wyoming');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T09:03').blur().should('have.value', '2023-07-25T09:03');

      cy.get(`[data-cy="updatedBy"]`).type('portals Inlet').should('have.value', 'portals Inlet');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        paymentConfig = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', paymentConfigPageUrlPattern);
    });
  });
});
