import { expect, type Locator, type Page } from '@playwright/test';

/** ProductsPage - inventario, carrito y checkout. */
export class ProductsPage {
  readonly title: Locator;
  readonly cartBadge: Locator;
  readonly cartLink: Locator;

  constructor(private readonly page: Page) {
    this.title = page.getByTestId('title');
    this.cartBadge = page.getByTestId('shopping-cart-badge');
    this.cartLink = page.getByTestId('shopping-cart-link');
  }

  async expectLoaded() {
    await expect(this.page).toHaveURL(/inventory\.html/);
    await expect(this.title).toHaveText('Products');
  }

  async addToCart(slug: string) {
    await this.page.getByTestId(`add-to-cart-${slug}`).click();
  }

  async removeFromCart(slug: string) {
    await this.page.getByTestId(`remove-${slug}`).click();
  }

  async checkout(firstName: string, lastName: string, postalCode: string) {
    await this.cartLink.click();
    await this.page.getByTestId('checkout').click();
    await this.page.getByTestId('firstName').fill(firstName);
    await this.page.getByTestId('lastName').fill(lastName);
    await this.page.getByTestId('postalCode').fill(postalCode);
    await this.page.getByTestId('continue').click();
    await this.page.getByTestId('finish').click();
  }

  get completeHeader(): Locator {
    return this.page.getByTestId('complete-header');
  }
}
