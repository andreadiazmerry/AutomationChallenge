import { test, expect } from './fixtures';
import { PASSWORD, customer } from './data/users';

const BACKPACK = 'sauce-labs-backpack';

test.describe('Pagina productos @products', () => {
  test.beforeEach(async ({ loginPage, productsPage }) => {
    await loginPage.login('standard_user', PASSWORD);
    await productsPage.expectLoaded();
  });

  test('agregar producto al carrito', async ({ productsPage }) => {
    await productsPage.addToCart(BACKPACK);
    await expect(productsPage.cartBadge).toHaveText('1');
  });

  test('eliminar producto del carrito', async ({ productsPage }) => {
    await productsPage.addToCart(BACKPACK);
    await productsPage.removeFromCart(BACKPACK);
    await expect(productsPage.cartBadge).toBeHidden();
  });

  test('checkout completo', async ({ productsPage }) => {
    await productsPage.addToCart(BACKPACK);
    await productsPage.checkout(customer.firstName, customer.lastName, customer.postalCode);
    await expect(productsPage.completeHeader).toHaveText('Thank you for your order!');
  });
});
