import { expect, test } from '@playwright/test';

test('footer displays Local Motion logo', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.waitForTimeout(1000);
  const img = page.getByRole('img', { name: 'footer logo' });
  await img.waitFor({ state: 'visible'})
});

test('footer displays major headings', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByRole('heading', { name: 'Admin Login' }).click();
  await expect(page).toHaveURL(/.*accounts.google.com\/.*/);

  await page.goto('http://localhost:3000/');
  await page.getByRole('heading', { name: 'About Us' }).click();
});

test('footer displays about sub links', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByRole('link', { name: 'GitHub Repo' }).click();
  await expect(page).toHaveURL('https://github.com/UnityFoundation-io/LocalMotionWeMove');
});

test('footer has facebook icon', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByRole('img', { name: 'facebook' }).click();
  await expect(page).toHaveURL('https://www.facebook.com/LocalMotionCoMo/');
});

test('footer has instagram icon', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByRole('img', { name: 'instagram' }).click();
  await expect(page).toHaveURL(/https:\/\/www.instagram.com\/.*localmotioncomo.*/);
});

test('footer has twitter icon', async ({ page }) => {
  await page.goto('http://localhost:3000/');
  await page.getByRole('img', { name: 'twitter' }).click();
  await expect(page).toHaveURL(/https:\/\/twitter\.com\/.*localmotioncomo/);
});