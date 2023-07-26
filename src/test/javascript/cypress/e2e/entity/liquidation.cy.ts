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

describe('Liquidation e2e test', () => {
  const liquidationPageUrl = '/liquidation';
  const liquidationPageUrlPattern = new RegExp('/liquidation(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const liquidationSample = {
    serviceLevel: 'P',
    timestamp: '2023-07-25T20:13:36.957Z',
    partnerCode: 'Outdoors Soap Summit',
    amount: 'Pass',
    currency: 'turquoise hub',
    receiverBankcode: 'Knoll',
    receiverAccountNumber: 'Automotive Customer groupware',
    beneficiaryName: 'Specialist Account',
    instructionId: 'web-enabled',
    senderToReceiverInfo: 'back generate',
    freeText1: 'Dollar',
    freeText2: 'indexing',
    freeText3: 'HDD',
    freeText4: 'Regional 5th index',
    freeText5: 'Jewelery synthesizing executive',
    freeText6: 'Buckinghamshire',
    freeText7: 'calculate connect withdrawal',
    freeText8: 'Persevering Secured',
    freeText9: 'enterprise',
    freeText10: 'quantify',
    freeText11: 'Nicaragua Cheese reboot',
    freeText12: 'Refined models Mouse',
    freeText13: 'Steel',
    freeText14: 'Jersey',
    freeText15: 'port partnerships',
    freeText16: 'Buckinghamshire invoice Account',
    freeText17: 'Montana Chair Future-proofed',
    freeText18: 'Analyst',
    freeText19: '1080p multi-byte enhance',
    freeText20: 'Car',
    freeText21: 'Tuna',
    freeText22: 'Reduced HTTP',
    freeText23: 'Tools Account Malta',
    freeText24: 'Checking',
    freeText25: 'paradigms redundant',
    freeText26: 'deposit seize Throughway',
    freeText27: 'Program Account Colorado',
    freeText28: 'frictionless Frozen Dynamic',
  };

  let liquidation;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/liquidations+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/liquidations').as('postEntityRequest');
    cy.intercept('DELETE', '/api/liquidations/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (liquidation) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/liquidations/${liquidation.id}`,
      }).then(() => {
        liquidation = undefined;
      });
    }
  });

  it('Liquidations menu should load Liquidations page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('liquidation');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Liquidation').should('exist');
    cy.url().should('match', liquidationPageUrlPattern);
  });

  describe('Liquidation page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(liquidationPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Liquidation page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/liquidation/new$'));
        cy.getEntityCreateUpdateHeading('Liquidation');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', liquidationPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/liquidations',
          body: liquidationSample,
        }).then(({ body }) => {
          liquidation = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/liquidations+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [liquidation],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(liquidationPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Liquidation page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('liquidation');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', liquidationPageUrlPattern);
      });

      it('edit button click should load edit Liquidation page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Liquidation');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', liquidationPageUrlPattern);
      });

      it('edit button click should load edit Liquidation page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Liquidation');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', liquidationPageUrlPattern);
      });

      it('last delete button click should delete instance of Liquidation', () => {
        cy.intercept('GET', '/api/liquidations/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('liquidation').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', liquidationPageUrlPattern);

        liquidation = undefined;
      });
    });
  });

  describe('new Liquidation page', () => {
    beforeEach(() => {
      cy.visit(`${liquidationPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Liquidation');
    });

    it('should create an instance of Liquidation', () => {
      cy.get(`[data-cy="recordId"]`).type('withdrawal Bedfordshire').should('have.value', 'withdrawal Bedfordshire');

      cy.get(`[data-cy="serviceLevel"]`).select('Y');

      cy.get(`[data-cy="timestamp"]`).type('2023-07-24T23:54').blur().should('have.value', '2023-07-24T23:54');

      cy.get(`[data-cy="partnerCode"]`).type('alarm Card').should('have.value', 'alarm Card');

      cy.get(`[data-cy="amount"]`).type('Spring Cheese').should('have.value', 'Spring Cheese');

      cy.get(`[data-cy="currency"]`).type('Jordan impactful rich').should('have.value', 'Jordan impactful rich');

      cy.get(`[data-cy="receiverBankcode"]`).type('Chips Shoes').should('have.value', 'Chips Shoes');

      cy.get(`[data-cy="receiverAccountNumber"]`)
        .type('functionalities eco-centric Handcrafted')
        .should('have.value', 'functionalities eco-centric Handcrafted');

      cy.get(`[data-cy="beneficiaryName"]`).type('drive').should('have.value', 'drive');

      cy.get(`[data-cy="instructionId"]`).type('strategize programming').should('have.value', 'strategize programming');

      cy.get(`[data-cy="senderToReceiverInfo"]`).type('Down-sized models navigate').should('have.value', 'Down-sized models navigate');

      cy.get(`[data-cy="freeText1"]`).type('parsing').should('have.value', 'parsing');

      cy.get(`[data-cy="freeText2"]`).type('executive Right-sized Bypass').should('have.value', 'executive Right-sized Bypass');

      cy.get(`[data-cy="freeText3"]`).type('Frozen Chips').should('have.value', 'Frozen Chips');

      cy.get(`[data-cy="freeText4"]`).type('Account Books Plastic').should('have.value', 'Account Books Plastic');

      cy.get(`[data-cy="freeText5"]`).type('Shoes').should('have.value', 'Shoes');

      cy.get(`[data-cy="freeText6"]`).type('overriding Sleek eyeballs').should('have.value', 'overriding Sleek eyeballs');

      cy.get(`[data-cy="freeText7"]`).type('hacking invoice').should('have.value', 'hacking invoice');

      cy.get(`[data-cy="freeText8"]`).type('full-range').should('have.value', 'full-range');

      cy.get(`[data-cy="freeText9"]`).type('Re-contextualized Steel').should('have.value', 'Re-contextualized Steel');

      cy.get(`[data-cy="freeText10"]`).type('Berkshire').should('have.value', 'Berkshire');

      cy.get(`[data-cy="freeText11"]`).type('invoice').should('have.value', 'invoice');

      cy.get(`[data-cy="freeText12"]`).type('Licensed Synchronised').should('have.value', 'Licensed Synchronised');

      cy.get(`[data-cy="freeText13"]`).type('Berkshire bi-directional').should('have.value', 'Berkshire bi-directional');

      cy.get(`[data-cy="freeText14"]`).type('Metal Checking Loaf').should('have.value', 'Metal Checking Loaf');

      cy.get(`[data-cy="freeText15"]`).type('invoice Ergonomic').should('have.value', 'invoice Ergonomic');

      cy.get(`[data-cy="freeText16"]`).type('Wooden').should('have.value', 'Wooden');

      cy.get(`[data-cy="freeText17"]`).type('Islands').should('have.value', 'Islands');

      cy.get(`[data-cy="freeText18"]`).type('Technician indexing').should('have.value', 'Technician indexing');

      cy.get(`[data-cy="freeText19"]`).type('Bedfordshire Greenland Ergonomic').should('have.value', 'Bedfordshire Greenland Ergonomic');

      cy.get(`[data-cy="freeText20"]`).type('North hour Internal').should('have.value', 'North hour Internal');

      cy.get(`[data-cy="freeText21"]`).type('Digitized').should('have.value', 'Digitized');

      cy.get(`[data-cy="freeText22"]`).type('Grocery Bangladesh group').should('have.value', 'Grocery Bangladesh group');

      cy.get(`[data-cy="freeText23"]`).type('Tools Technician').should('have.value', 'Tools Technician');

      cy.get(`[data-cy="freeText24"]`).type('Berkshire Causeway').should('have.value', 'Berkshire Causeway');

      cy.get(`[data-cy="freeText25"]`).type('Louisiana hacking Bacon').should('have.value', 'Louisiana hacking Bacon');

      cy.get(`[data-cy="freeText26"]`).type('Knolls').should('have.value', 'Knolls');

      cy.get(`[data-cy="freeText27"]`).type('Chips Ball').should('have.value', 'Chips Ball');

      cy.get(`[data-cy="freeText28"]`).type('compressing Home').should('have.value', 'compressing Home');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T10:33').blur().should('have.value', '2023-07-25T10:33');

      cy.get(`[data-cy="createdBy"]`).type('Cambridgeshire').should('have.value', 'Cambridgeshire');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T04:57').blur().should('have.value', '2023-07-25T04:57');

      cy.get(`[data-cy="updatedBy"]`).type('capacitor').should('have.value', 'capacitor');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        liquidation = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', liquidationPageUrlPattern);
    });
  });
});
