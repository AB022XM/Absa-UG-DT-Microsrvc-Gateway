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

describe('Devices e2e test', () => {
  const devicesPageUrl = '/devices';
  const devicesPageUrlPattern = new RegExp('/devices(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const devicesSample = {
    deviceName: 'Usability Michigan Cambridgeshire',
    deviceType: 'Fresh mesh Summit',
    timestamp: '2023-07-25T10:40:07.658Z',
  };

  let devices;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/devices+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/devices').as('postEntityRequest');
    cy.intercept('DELETE', '/api/devices/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (devices) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/devices/${devices.id}`,
      }).then(() => {
        devices = undefined;
      });
    }
  });

  it('Devices menu should load Devices page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('devices');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Devices').should('exist');
    cy.url().should('match', devicesPageUrlPattern);
  });

  describe('Devices page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(devicesPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Devices page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/devices/new$'));
        cy.getEntityCreateUpdateHeading('Devices');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', devicesPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/devices',
          body: devicesSample,
        }).then(({ body }) => {
          devices = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/devices+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [devices],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(devicesPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Devices page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('devices');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', devicesPageUrlPattern);
      });

      it('edit button click should load edit Devices page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Devices');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', devicesPageUrlPattern);
      });

      it('edit button click should load edit Devices page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Devices');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', devicesPageUrlPattern);
      });

      it('last delete button click should delete instance of Devices', () => {
        cy.intercept('GET', '/api/devices/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('devices').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', devicesPageUrlPattern);

        devices = undefined;
      });
    });
  });

  describe('new Devices page', () => {
    beforeEach(() => {
      cy.visit(`${devicesPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Devices');
    });

    it('should create an instance of Devices', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('756b54b0-5f01-4ce5-b043-03ca23d8a84b')
        .invoke('val')
        .should('match', new RegExp('756b54b0-5f01-4ce5-b043-03ca23d8a84b'));

      cy.get(`[data-cy="recordId"]`).type('metrics white Cameroon').should('have.value', 'metrics white Cameroon');

      cy.get(`[data-cy="deviceId"]`).type('Syrian Territory firewall').should('have.value', 'Syrian Territory firewall');

      cy.get(`[data-cy="deviceName"]`).type('open-source Rica').should('have.value', 'open-source Rica');

      cy.get(`[data-cy="deviceType"]`).type('Tennessee').should('have.value', 'Tennessee');

      cy.get(`[data-cy="deviceDescription"]`).type('primary multi-byte invoice').should('have.value', 'primary multi-byte invoice');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T17:30').blur().should('have.value', '2023-07-25T17:30');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="recordStatus"]`).select('ACTIVE');

      cy.get(`[data-cy="freeField"]`).type('Bypass program').should('have.value', 'Bypass program');

      cy.get(`[data-cy="freeField1"]`).type('Group sky orchid').should('have.value', 'Group sky orchid');

      cy.get(`[data-cy="freeField2"]`).type('Argentina Netherlands markets').should('have.value', 'Argentina Netherlands markets');

      cy.get(`[data-cy="freeField3"]`).type('up zero').should('have.value', 'up zero');

      cy.get(`[data-cy="freeField4"]`).type('end-to-end').should('have.value', 'end-to-end');

      cy.get(`[data-cy="freeField5"]`).type('panel Rubber').should('have.value', 'panel Rubber');

      cy.get(`[data-cy="freeField6"]`).type('pixel').should('have.value', 'pixel');

      cy.get(`[data-cy="freeField7"]`).type('Small efficient array').should('have.value', 'Small efficient array');

      cy.get(`[data-cy="freeField8"]`).type('California Legacy').should('have.value', 'California Legacy');

      cy.get(`[data-cy="freeField9"]`).type('Research').should('have.value', 'Research');

      cy.get(`[data-cy="freeField10"]`).type('SAS bypassing Bedfordshire').should('have.value', 'SAS bypassing Bedfordshire');

      cy.get(`[data-cy="freeField11"]`).type('Baby Industrial').should('have.value', 'Baby Industrial');

      cy.get(`[data-cy="freeField12"]`).type('Cambridgeshire optical Credit').should('have.value', 'Cambridgeshire optical Credit');

      cy.get(`[data-cy="freeField13"]`).type('generate').should('have.value', 'generate');

      cy.get(`[data-cy="freeField14"]`).type('Games Phased').should('have.value', 'Games Phased');

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

      cy.get(`[data-cy="freeField19"]`).type('Designer Toys explicit').should('have.value', 'Designer Toys explicit');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T19:30').blur().should('have.value', '2023-07-25T19:30');

      cy.get(`[data-cy="createdBy"]`).type('Turkish transmitter').should('have.value', 'Turkish transmitter');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T15:08').blur().should('have.value', '2023-07-25T15:08');

      cy.get(`[data-cy="updatedBy"]`).type('Chips Principal').should('have.value', 'Chips Principal');

      // since cypress clicks submit too fast before the blob fields are validated
      cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        devices = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', devicesPageUrlPattern);
    });
  });
});
