@TestTag
Feature: Tap
  I want to tap Views item

	@TestTag1
  Scenario Outline: Tap Views Item
    Given The application load successfully
    When I tap the Views item
    Then I capture a screenshot

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      