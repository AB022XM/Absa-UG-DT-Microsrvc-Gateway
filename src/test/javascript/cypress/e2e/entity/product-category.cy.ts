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

describe('ProductCategory e2e test', () => {
  const productCategoryPageUrl = '/product-category';
  const productCategoryPageUrlPattern = new RegExp('/product-category(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const productCategorySample = {
    productCategoryCode: 'partnerships Technician mindshare',
    productCategoryName: 'Human Developer Profound',
  };

  let productCategory;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/product-categories+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/product-categories').as('postEntityRequest');
    cy.intercept('DELETE', '/api/product-categories/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (productCategory) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/product-categories/${productCategory.id}`,
      }).then(() => {
        productCategory = undefined;
      });
    }
  });

  it('ProductCategories menu should load ProductCategories page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('product-category');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('ProductCategory').should('exist');
    cy.url().should('match', productCategoryPageUrlPattern);
  });

  describe('ProductCategory page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(productCategoryPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create ProductCategory page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/product-category/new$'));
        cy.getEntityCreateUpdateHeading('ProductCategory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productCategoryPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/product-categories',
          body: productCategorySample,
        }).then(({ body }) => {
          productCategory = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/product-categories+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [productCategory],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(productCategoryPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details ProductCategory page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('productCategory');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productCategoryPageUrlPattern);
      });

      it('edit button click should load edit ProductCategory page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ProductCategory');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productCategoryPageUrlPattern);
      });

      it('edit button click should load edit ProductCategory page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('ProductCategory');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productCategoryPageUrlPattern);
      });

      it('last delete button click should delete instance of ProductCategory', () => {
        cy.intercept('GET', '/api/product-categories/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('productCategory').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productCategoryPageUrlPattern);

        productCategory = undefined;
      });
    });
  });

  describe('new ProductCategory page', () => {
    beforeEach(() => {
      cy.visit(`${productCategoryPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('ProductCategory');
    });

    it('should create an instance of ProductCategory', () => {
      cy.get(`[data-cy="absaTranRef"]`)
        .type('49ce19cb-e9a9-49d6-a081-4bbf85ba19fb')
        .invoke('val')
        .should('match', new RegExp('49ce19cb-e9a9-49d6-a081-4bbf85ba19fb'));

      cy.get(`[data-cy="billerId"]`).type('e-services Samoa TCP').should('have.value', 'e-services Samoa TCP');

      cy.get(`[data-cy="recordId"]`).type('Bedfordshire').should('have.value', 'Bedfordshire');

      cy.get(`[data-cy="productCategoryCode"]`).type('Metal Global administration').should('have.value', 'Metal Global administration');

      cy.get(`[data-cy="productCategoryName"]`).type('Infrastructure Personal').should('have.value', 'Infrastructure Personal');

      cy.get(`[data-cy="productCategoryDescription"]`).type('Italy Sleek Car').should('have.value', 'Italy Sleek Car');

      cy.get(`[data-cy="productCategoryImage"]`).type('tan Armenia').should('have.value', 'tan Armenia');

      cy.get(`[data-cy="productFreeField1"]`).type('Product').should('have.value', 'Product');

      cy.get(`[data-cy="productFreeField2"]`)
        .type('../fake-data/blob/hipster.txt')
        .invoke('val')
        .should('match', new RegExp('../fake-data/blob/hipster.txt'));

      cy.get(`[data-cy="productFreeField3"]`).type('Business-focused Maryland').should('have.value', 'Business-focused Maryland');

      cy.get(`[data-cy="productFreeField4"]`).type('Litas Home Cedi').should('have.value', 'Litas Home Cedi');

      cy.get(`[data-cy="productFreeField5"]`).type('help-desk deposit').should('have.value', 'help-desk deposit');

      cy.get(`[data-cy="productFreeField6"]`).type('array Arkansas').should('have.value', 'array Arkansas');

      cy.get(`[data-cy="productFreeField7"]`).type('input').should('have.value', 'input');

      cy.get(`[data-cy="productFreeField8"]`).type('Soft up').should('have.value', 'Soft up');

      cy.get(`[data-cy="productFreeField9"]`).type('Montana real-time Congolese').should('have.value', 'Montana real-time Congolese');

      cy.get(`[data-cy="productFreeField10"]`).type('Coordinator').should('have.value', 'Coordinator');

      cy.get(`[data-cy="productFreeField11"]`).type('RSS').should('have.value', 'RSS');

      cy.get(`[data-cy="delflg"]`).should('not.be.checked');
      cy.get(`[data-cy="delflg"]`).click().should('be.checked');

      cy.get(`[data-cy="createdAt"]`).type('2023-07-25T17:33').blur().should('have.value', '2023-07-25T17:33');

      cy.get(`[data-cy="createdBy"]`).type('payment Handmade').should('have.value', 'payment Handmade');

      cy.get(`[data-cy="updatedAt"]`).type('2023-07-25T18:44').blur().should('have.value', '2023-07-25T18:44');

      cy.get(`[data-cy="updatedBy"]`).type('synthesize Ethiopian Music').should('have.value', 'synthesize Ethiopian Music');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        productCategory = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', productCategoryPageUrlPattern);
    });
  });
});
