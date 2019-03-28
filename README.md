# SOEN 6441 - BUILD 2
| Criteria      |Points         |     | Readiness     |
| ------------- |:-------------|:-------------:|:-------------:|
|Presentation |5||100%|
|Effectiveness, structure and demonstrated preparation of the presentation |2||100%|
|Fluid exposition of knowledge of code base/clarity of explanations |3||100%|
|Functional Requirements |30||100%|
|Map editor |5||100%|
|User-driven creation of map elements, such as country, continent, and connectivity between countries. |2||100%|
|Saving a map to a file exactly as edited (using the “conquest” game map format). |1||100%|
|Loading a map from an existing “conquest” map file, then editing the map, or create a new map from scratch. |1||100%|
|Verification of map correctness upon loading and before saving (at least 3 types of incorrect maps, including unconnected map and unconnected continent). |1||100%|
|Game Play |25||100%|
|Implementation of a “phase view” using the Observer pattern. The phase view should display: (1) the name of the game phase currently being played (2) the current player’s name (3) information about actions that are taking place during this phase. The phase view should be cleared at the beginning of every phase.|3||100%|
|Implementation of a “players world domination view” using the Observer pattern. The players world domination view should display (1) the percentage of the map controlled by every player (2) the continents controlled by every player (3) the totalnumber of armies owned by every player.|3||100%|
|Implementation of the reinforcement, attack and fortification as methods of the Player class. |3||100%|
|Startup phase |2||100%|
|Game starts by user selection of a user-saved map file, then loads the map as a connected graph. User chooses the number of players, then all countries are randomly assigned to players. Players are allocated a number of initial armies depending on the number of players. In round-robin fashion, the players place one by one their given armies on their own countries.|2||100%|
|Reinforcement phase |4||100%|
|Calculation of correct number of reinforcement armies according to the Risk rules. Players place reinforcement armies on the map.|1||100%|
|Implementation of a “card exchange view” using the Observer pattern. The card exchange view should be created only during the reinforcement phase. It should display all the cards owned by the current player, then allow the player to select some cards to exchange. If the player selects cards, they are given the appropriate number of armies as reinforcement. The player can choose not to exchange cards and exit the card exchange view. If the player own 5 cards or more, they must exchange cards. The cards exchange view should cease to exist after the cards exchange.|3||100%|
|Attack phase |8||100%|
|Player can declare an attack by selecting attacker and attacked country. |1||100%|
|Attacker and attacked player decide how many dice to roll. |1||100%|
|Proper number of armies are deducted from attacker/defender country during the attack(s). |2||100%|
|If defender is conquered, attacker can move any number of its armies in the conquered country (see the Risk rules). If it results in conquering the whole map, the attacker is declared the winner and the game ends. |1||100%|
|Player may decide to attack or not to attack again. If attack not possible, attack automatically ends. |1||100%|
|Implementation of an “all-out” mode, where once the attacker and attacked country have been identified, the attack proceeds with maximum number of dice and end only when either the attacker conquers the attacked, or the attacker cannot attack anymore.|2||100%|
|Fortification phase |2||100%|
|Implementation of a valid fortification move according to the Risk rules. |2||100%|
