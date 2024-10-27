@Test

Feature: Actualizar usuario

  Scenario: Actualizar usuario con ID valido y con request body valido
    Given actualizar usuario con ID valido and con request body valido
    When enviar solicitud actualizar usuario
    Then debería devolver el codigo 200
    And validar usuario json schema

  Scenario: Actualizar usuario con ID no registrado y request body valido
    Given actualizar usuario con "1" como ID y "valid" request body
    When enviar solicitud actualizar usuario
    Then debería devolver el codigo 404

  Scenario: Actualizar usuario sin token
    Given actualizar usuario sin token
    When enviar solicitud actualizar usuario
    Then debería devolver el codigo 401
#    And mensaje del response body message deberia ser "Autentication failed"
#    And validar json schema response error

  Scenario: Actualizar usuario sin ID
    Given actualizar usuario con "" como ID y "valid" request body
    When enviar solicitud actualizar usuario
    Then debería devolver el codigo 404