import { AppPage } from './app.po';
import { browser, logging, by, element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });



  it('should display title of Application ', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayersUI');
  });

  /*it('should be able to redirect to /register route ', () => {
    browser.element(by.css('.register-button')).click();
    browser.driver.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(2000);
  });

  it('should be able to register user ', () => {
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('email')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.register-user')).click();
    browser.driver.sleep(2000);
  });*/

  it('should be able to  login', () => {
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.login-click')).click();

  });

  it('should be able to get default list ', () => {
    browser.element(by.css('.mat-button')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-player')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/Player');
    browser.driver.sleep(500);
  });

  it('should be able to open player details ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.detailbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to  close player details ', () => {
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.close1')).click();
    browser.driver.sleep(1000);
  });


  it('should be able to save Default List to Favorite List ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });


  it('should be able to get all data from Favorite ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-favorite')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/Favorite');

  });


  it('should be able to get details ', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-favorite')).click();
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/Favorite');

  });

  it('should be able to delete data from Favorite ', () => {
    // browser.driver.manage().window().maximize();
    // browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-favorite')).click();
    browser.driver.sleep(2000);
    browser.driver.manage().window().maximize();
    const tracks = element.all(by.css('.example-card'));
    browser.driver.sleep(2000);
    browser.element(by.css('.deletebutton')).click();
    browser.driver.sleep(2000);

  });

  it('should be able to see recommened list ', () => {
    // browser.driver.manage().window().maximize();
    browser.element(by.css('.mat-button-recommended')).click();
    browser.driver.sleep(2000);
    browser.driver.manage().window().maximize();

  });


  it('should be able to logout ', () => {
    // browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-logout')).click();
    browser.driver.sleep(1000);

  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
