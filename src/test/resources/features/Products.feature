Feature: Pagina productos
  Background:
    Given El usuario ha iniciado sesión correctamente

  Scenario Outline: Diferentes acciones con productos
    When El usuario debe ver la página de productos
    Then El usuario puede "<accion>" un producto

    Examples:
      | accion       |
      | agregar      |
      | eliminar     |
      | checkout     |


