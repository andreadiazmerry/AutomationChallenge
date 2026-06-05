Feature: Login de usuario

  Background:
    Given El usuario está en la página de login

  @Login
  Scenario Outline: Login exitoso con credenciales válidas
    When El usuario ingresa el "<username>" y "<password>"
    And El usuario hace click en el botón de login
    Then El usuario debe ver la página de productos

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  Scenario Outline: Login con credenciales inválidas
    When El usuario ingresa el "<username>" y "<password>"
    And El usuario hace click en el botón de login
    Then El usuario debe ver "<mensaje>" de error

    Examples:
      | username                | password     | mensaje                                                                   |
      |                         | secret_sauce | Epic sadface: Username is required                                        |
      | locked_out_user         |              | Epic sadface: Password is required                                        |
      | wrong_user              | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | performance_glitch_user | wrong_sauce  | Epic sadface: Username and password do not match any user in this service |