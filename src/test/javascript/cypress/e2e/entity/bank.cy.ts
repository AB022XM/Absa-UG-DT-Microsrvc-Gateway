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

describe('Bank e2e test', () => {
  const bankPageUrl = '/bank';
  const bankPageUrlPattern = new RegExp('/bank(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const bankSample = {
    billerId: 'AGP',
    paymentItemCode: 'Rubber Highway Franc',
    bankName: '24/7 olive override',
    bankSwiftCode: 'input leading-edge JBOD',
    createdAt: '2023-07-25T01:01:33.655Z',
  };

  let bank;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/banks+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/banks').as('postEntityRequest');
    cy.intercept('DELETE', '/api/banks/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (bank) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/banks/${bank.id}`,
      }).then(() => {
        bank = undefined;
      });
    }
  });

  it('Banks menu should load Banks page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('bank');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Bank').should('exist');
    cy.url().should('match', bankPageUrlPattern);
  });

  describe('Bank page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(bankPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Bank page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/bank/new$'));
        cy.getEntityCreateUpdateHeading('Bank');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', bankPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/banks',
          body: bankSample,
        }).then(({ body }) => {
          bank = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/banks+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [bank],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(bankPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Bank page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('bank');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', bankPageUrlPattern);
      });

      it('edit button click should load edit Bank page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Bank');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', bankPageUrlPattern);
      });

      it('edit button click should load edit Bank page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Bank');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', bankPageUrlPattern);
      });

      it('last delete button click should delete instance of Bank', () => {
        cy.intercept('GET', '/api/banks/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('bank').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', bankPageUrlPattern);

        bank = undefined;
      });
    });
  });

  describe('new Bank page', () => {
    beforeEach(() => {
      cy.visit(`${bankPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Bank');
    });

    it('should create an instance of Bank', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('2c8b4e56-bf38-4e64-9f5e-91d7aa24f074')
        .invoke('val')
        .should('match', new RegExp('2c8b4e56-bf38-4e64-9f5e-91d7aa24f074'));

      cy.get(`[data-cy="billerId"]`).type('Fort Rustic Bacon').should('have.value', 'Fort Rustic Bacon');

      cy.get(`[data-cy="paymentItemCode"]`).type('navigating Business-focused').should('have.value', 'navigating Business-focused');

      cy.get(`[data-cy="dtDTransactionId"]`)
        .type('compressing Operative mission-critical')
        .should('have.value', 'compressing Operative mission-critical');

      cy.get(`[data-cy="amolDTransactionId"]`).type('deploy niches').should('have.value', 'deploy niches');

      cy.get(`[data-cy="bankName"]`).type('Borders Executive Western').should('have.value', 'Borders Executive Western');

      cy.get(`[data-cy="bankSwiftCode"]`).type('Birr circuit USB').should('have.value', 'Birr circuit USB');

      cy.get(`[data-cy="bankBranchId"]`).type('frictionless circuit').should('have.value', 'frictionless circuit');

      cy.get(`[data-cy="bankPhoneNumber"]`).type('Product reboot').should('have.value', 'Product reboot');

      cy.get(`[data-cy="bankEmail"]`).type('Bolivar Lake').should('have.value', 'Bolivar Lake');

      cy.get(`[data-cy="bankFreeField1"]`).type('Incredible').should('have.value', 'Incredible');

      cy.get(`[data-cy="bankFreeField3"]`).type('optical').should('have.value', 'optical');

      cy.get(`[data-cy="bankFreeField4"]`).type('Rubber').should('have.value', 'Rubber');

      cy.get(`[data-cy="bankFreeField5"]`).type('cross-platform Account').should('have.value', 'cross-platform Account');

      cy.get(`[data-cy="bankFreeField6"]`).type('Movies Manat').should('have.value', 'Movies Manat');

      cy.get(`[data-cy="bankFreeField7"]`).type('program SMTP').should('have.value', 'program SMTP');

      cy.get(`[data-cy="bankFreeField8"]`).type('Home Wooden FTP').should('have.value', 'Home Wooden FTP');

      cy.get(`[data-cy="bankFreeField9"]`).type('Generic program Checking').should('have.value', 'Generic program Checking');

      cy.get(`[data-cy="bankFreeField10"]`).type('Chief Balboa initiative').should('have.value', 'Chief Balboa initiative');

      cy.get(`[data-cy="bankFreeField11"]`).type('enterprise').should('have.value', 'enterprise');

      cy.get(`[data-cy="bankFreeField12"]`).type('index').should('have.value', 'index');

      cy.get(`[data-cy="bankFreeField13"]`).type('index Bedfordshire Springs').should('have.value', 'index Bedfordshire Springs');

      cy.get(`[data-cy="bankFreeField14"]`).type('Junctions').should('have.value', 'Junctions');

      cy.get(`[data-cy="bankFreeField15"]`).type('Senior real-time Branding').should('have.value', 'Senior real-time Branding');

      cy.get(`[data-cy="bankFreeField16"]`).type('instruction').should('have.value', 'instruction');

      cy.get(`[data-cy="bankFreeField17"]`).type('transmitting Plastic').should('have.value', 'transmitting Plastic');

      cy.get(`[data-cy="bankFreeField18"]`).type('port bus').should('have.value', 'port bus');

      cy.get(`[data-cy="bankFreeField19"]`).type('Strategist').should('have.value', 'Strategist');

      cy.get(`[data-cy="bankFreeField20"]`).type('CSS RSS Tools').should('have.value', 'CSS RSS Tools');

      cy.get(`[data-cy="bankFreeField21"]`).type('Pennsylvania visualize').should('have.value', 'Pennsylvania visualize');

      cy.get(`[data-cy="bankFreeField22"]`).type('sexy Usability').should('have.value', 'sexy Usability');

      cy.get(`[data-cy="bankFreeField23"]`).type('Nevada').should('have.value', 'Nevada');

      cy.get(`[data-cy="bankFreeField24"]`).type('Bacon').should('have.value', 'Bacon');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T06:39').blur().should('have.value', '2023-07-25T06:39');

      cy.get(`[data-cy="createdBy"]`).type('Wooden driver deposit').should('have.value', 'Wooden driver deposit');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T09:08').blur().should('have.value', '2023-07-25T09:08');

      cy.get(`[data-cy="updatedBy"]`).type('Grove Handmade').should('have.value', 'Grove Handmade');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="status"]`).select('PENDING');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        bank = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', bankPageUrlPattern);
    });
  });
});
