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

describe('DTransactionChannel e2e test', () => {
  const dTransactionChannelPageUrl = '/d-transaction-channel';
  const dTransactionChannelPageUrlPattern = new RegExp('/d-transaction-channel(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dTransactionChannelSample = {
    billerId: 'bluetooth Bahamas cultivate',
    paymentItemCode: 'transform Ergonomic',
    channelCode: 'unleash',
    channelName: 'grey invoice',
  };

  let dTransactionChannel;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-transaction-channels+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-transaction-channels').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-transaction-channels/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dTransactionChannel) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-transaction-channels/${dTransactionChannel.id}`,
      }).then(() => {
        dTransactionChannel = undefined;
      });
    }
  });

  it('DTransactionChannels menu should load DTransactionChannels page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-transaction-channel');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DTransactionChannel').should('exist');
    cy.url().should('match', dTransactionChannelPageUrlPattern);
  });

  describe('DTransactionChannel page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dTransactionChannelPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DTransactionChannel page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-transaction-channel/new$'));
        cy.getEntityCreateUpdateHeading('DTransactionChannel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionChannelPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-transaction-channels',
          body: dTransactionChannelSample,
        }).then(({ body }) => {
          dTransactionChannel = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-transaction-channels+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [dTransactionChannel],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(dTransactionChannelPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DTransactionChannel page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dTransactionChannel');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionChannelPageUrlPattern);
      });

      it('edit button click should load edit DTransactionChannel page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionChannel');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionChannelPageUrlPattern);
      });

      it('edit button click should load edit DTransactionChannel page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DTransactionChannel');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionChannelPageUrlPattern);
      });

      it('last delete button click should delete instance of DTransactionChannel', () => {
        cy.intercept('GET', '/api/d-transaction-channels/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dTransactionChannel').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', dTransactionChannelPageUrlPattern);

        dTransactionChannel = undefined;
      });
    });
  });

  describe('new DTransactionChannel page', () => {
    beforeEach(() => {
      cy.visit(`${dTransactionChannelPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DTransactionChannel');
    });

    it('should create an instance of DTransactionChannel', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('efb98ec5-1dd4-458a-be57-820c719476e7')
        .invoke('val')
        .should('match', new RegExp('efb98ec5-1dd4-458a-be57-820c719476e7'));

      cy.get(`[data-cy="billerId"]`).type('Borders feed').should('have.value', 'Borders feed');

      cy.get(`[data-cy="paymentItemCode"]`).type('Configuration synthesizing').should('have.value', 'Configuration synthesizing');

      cy.get(`[data-cy="dtDTransactionId"]`).type('Music input').should('have.value', 'Music input');

      cy.get(`[data-cy="transactionReferenceNumber"]`).type('Nebraska Practical Avon').should('have.value', 'Nebraska Practical Avon');

      cy.get(`[data-cy="externalTranid"]`).type('Shirt Granite').should('have.value', 'Shirt Granite');

      cy.get(`[data-cy="channelCode"]`).type('New Virginia interactive').should('have.value', 'New Virginia interactive');

      cy.get(`[data-cy="channelName"]`).type('portals turn-key').should('have.value', 'portals turn-key');

      cy.get(`[data-cy="channelDescription"]`).type('Fantastic teal uniform').should('have.value', 'Fantastic teal uniform');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-25T02:01').blur().should('have.value', '2023-07-25T02:01');

      cy.get(`[data-cy="chanelFreeText"]`).type('firewall utilisation Global').should('have.value', 'firewall utilisation Global');

      cy.get(`[data-cy="chanelFreeText1"]`).type('Carolina').should('have.value', 'Carolina');

      cy.get(`[data-cy="chanelFreeText2"]`).type('Neck').should('have.value', 'Neck');

      cy.get(`[data-cy="chanelFreeText3"]`).type('SSL Pound').should('have.value', 'SSL Pound');

      cy.get(`[data-cy="chanelFreeText4"]`).type('alarm calculating Branding').should('have.value', 'alarm calculating Branding');

      cy.get(`[data-cy="chanelFreeText5"]`)
        .type('overriding info-mediaries Clothing')
        .should('have.value', 'overriding info-mediaries Clothing');

      cy.get(`[data-cy="chanelFreeText6"]`).type('bi-directional').should('have.value', 'bi-directional');

      cy.get(`[data-cy="chanelFreeText7"]`).type('mobile of').should('have.value', 'mobile of');

      cy.get(`[data-cy="chanelFreeText8"]`).type('Chair').should('have.value', 'Chair');

      cy.get(`[data-cy="chanelFreeText9"]`).type('whiteboard').should('have.value', 'whiteboard');

      cy.get(`[data-cy="chanelFreeText10"]`).type('Analyst').should('have.value', 'Analyst');

      cy.get(`[data-cy="chanelFreeText11"]`).type('cross-media Account').should('have.value', 'cross-media Account');

      cy.get(`[data-cy="chanelFreeText12"]`).type('interactive Drive').should('have.value', 'interactive Drive');

      cy.get(`[data-cy="chanelFreeText13"]`).type('Shoes').should('have.value', 'Shoes');

      cy.get(`[data-cy="chanelFreeText14"]`).type('hack integrated XML').should('have.value', 'hack integrated XML');

      cy.get(`[data-cy="chanelFreeText15"]`).type('Unbranded').should('have.value', 'Unbranded');

      cy.get(`[data-cy="chanelFreeText16"]`).type('South extensible Berkshire').should('have.value', 'South extensible Berkshire');

      cy.get(`[data-cy="chanelFreeText17"]`).type('Right-sized').should('have.value', 'Right-sized');

      cy.get(`[data-cy="chanelFreeText18"]`).type('Rupee data-warehouse Shoes').should('have.value', 'Rupee data-warehouse Shoes');

      cy.get(`[data-cy="chanelFreeText19"]`).type('to payment').should('have.value', 'to payment');

      cy.get(`[data-cy="chanelFreeText20"]`).type('withdrawal pixel index').should('have.value', 'withdrawal pixel index');

      cy.get(`[data-cy="chanelFreeText21"]`).type('plum').should('have.value', 'plum');

      cy.get(`[data-cy="chanelFreeText22"]`).type('Mandatory connect scale').should('have.value', 'Mandatory connect scale');

      cy.get(`[data-cy="chanelFreeText23"]`).type('Tobago Ariary').should('have.value', 'Tobago Ariary');

      cy.get(`[data-cy="chanelFreeText24"]`).type('Jewelery').should('have.value', 'Jewelery');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T06:15').blur().should('have.value', '2023-07-25T06:15');

      cy.get(`[data-cy="createdBy"]`).type('Sleek Music').should('have.value', 'Sleek Music');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T00:29').blur().should('have.value', '2023-07-25T00:29');

      cy.get(`[data-cy="updatedBy"]`).type('Hawaii Kentucky').should('have.value', 'Hawaii Kentucky');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        dTransactionChannel = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', dTransactionChannelPageUrlPattern);
    });
  });
});
