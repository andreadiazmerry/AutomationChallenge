PROYECTO PLAYWRIGHT + MCP (saucedemo.com)
=========================================

Proyecto de automatizacion E2E con Playwright Test (TypeScript) y el servidor
Playwright MCP (Model Context Protocol), que permite a un agente de IA
(Claude Code, VS Code Copilot, Cursor, etc.) controlar el navegador para
explorar la app, generar y depurar tests. Cubre los mismos escenarios que la
suite Selenium + Cucumber de la raiz del repositorio (Login y Productos).


ESTRUCTURA
----------
playwright-mcp/
  .mcp.json                   Registro del servidor MCP para Claude Code / Cursor
  .vscode/mcp.json            Registro del servidor MCP para VS Code
  playwright-mcp.config.json  Configuracion del servidor MCP (navegador, origenes)
  playwright.config.ts        Configuracion de Playwright Test
  tsconfig.json               Configuracion TypeScript
  package.json                Dependencias y scripts npm
  pages/
    LoginPage.ts              Page Object de login
    ProductsPage.ts           Page Object de productos / carrito / checkout
  tests/
    fixtures.ts               Fixtures que inyectan los Page Objects
    data/users.ts             Datos de prueba (usuarios, mensajes, cliente)
    login.spec.ts             Tests de login (validos e invalidos)  @login
    products.spec.ts          Tests de agregar, eliminar y checkout @products


PASOS SEGUIDOS PARA CONSTRUIRLO
-------------------------------
1. Revisar la suite Selenium + Cucumber existente (Login.feature,
   Products.feature, LoginPage.java, ProductsPage.java) para identificar la
   URL base (https://www.saucedemo.com) y los escenarios a replicar.

2. Crear la carpeta del proyecto e inicializar npm:
     mkdir playwright-mcp && cd playwright-mcp
     npm init -y

3. Instalar dependencias de desarrollo:
     npm i -D @playwright/test @playwright/mcp @types/node typescript

4. Descargar los navegadores de Playwright:
     npx playwright install chromium

5. Crear playwright.config.ts:
   - testDir ./tests, ejecucion en paralelo, reintentos solo en CI.
   - baseURL https://www.saucedemo.com (sobreescribible con BASE_URL).
   - testIdAttribute = 'data-test' (atributo que usa saucedemo) para usar
     page.getByTestId().
   - trace, screenshot y video solo en fallos; reporter list + html.
   - Proyecto chromium (Desktop Chrome).

6. Configurar el servidor Playwright MCP:
   - playwright-mcp.config.json: chromium, perfil aislado, navegador visible,
     carpeta de salida ./mcp-output y origen permitido saucedemo.com.
   - .mcp.json (Claude Code / Cursor) y .vscode/mcp.json (VS Code) registran
     el servidor "playwright" con:
       npx @playwright/mcp@latest --config playwright-mcp.config.json
   - Alternativa en Claude Code por linea de comandos:
       claude mcp add playwright -- npx @playwright/mcp@latest

7. Usar el agente con MCP para explorar la app: navegar a saucedemo, hacer
   login, agregar productos, checkout, y leer el snapshot de accesibilidad
   para obtener los selectores data-test estables (username, password,
   login-button, error, add-to-cart-*, remove-*, shopping-cart-badge,
   checkout, firstName, lastName, postalCode, continue, finish,
   complete-header).

8. Crear los Page Objects en pages/ (LoginPage, ProductsPage) con
   Locators basados en getByTestId.

9. Crear tests/data/users.ts con los datos de los Examples de los .feature
   (usuarios validos, combinaciones invalidas y mensajes de error).

10. Crear tests/fixtures.ts extendiendo el test base para inyectar
    loginPage (ya navegado a la home) y productsPage.

11. Escribir los specs:
    - login.spec.ts: un test por usuario valido y por login invalido
      (data-driven, equivalente al Scenario Outline).
    - products.spec.ts: agregar, eliminar y checkout completo, con login en
      beforeEach.
    - Tags @login y @products para filtrar con --grep.

12. Agregar scripts npm, tsconfig.json y .gitignore (node_modules,
    test-results, playwright-report, mcp-output).

13. Validar: npx tsc -p . (typecheck) y npx playwright test --list
    (12 tests en 2 archivos).


COMO EJECUTAR
-------------
  npm install
  npx playwright install chromium
  npm test                  Todos los tests (headless)
  npm run test:headed       Con navegador visible
  npm run test:ui           Modo UI interactivo
  npm run test:login        Solo @login
  npm run test:products     Solo @products
  npm run report            Abrir reporte HTML
  npm run typecheck         Verificar tipos
  npm run mcp               Levantar servidor MCP manualmente


COMO USAR EL MCP
----------------
Abrir la carpeta playwright-mcp en Claude Code, VS Code o Cursor; el cliente
detecta .mcp.json / .vscode/mcp.json y ofrece habilitar el servidor
"playwright". Luego pedirle al agente, por ejemplo:
  "Abre saucedemo, haz login con standard_user y genera un test de Playwright
   que ordene los productos por precio de menor a mayor usando los Page
   Objects de pages/."
El agente usa herramientas como browser_navigate, browser_snapshot,
browser_click y browser_type para interactuar y proponer el codigo.
