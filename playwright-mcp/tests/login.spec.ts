import { test, expect } from './fixtures';
import { PASSWORD, invalidLogins, validUsers } from './data/users';

test.describe('Login de usuario @login', () => {
  for (const username of validUsers) {
    test(`login exitoso: ${username}`, async ({ loginPage, productsPage }) => {
      await loginPage.login(username, PASSWORD);
      await productsPage.expectLoaded();
    });
  }

  for (const { username, password, message } of invalidLogins) {
    test(`login invalido: "${username}" / "${password}"`, async ({ loginPage }) => {
      await loginPage.login(username, password);
      await expect(loginPage.errorMessage).toHaveText(message);
    });
  }
});
