---
layout: page
title: Peng Xiang's Project Portfolio Page
---

## Project: FlashNotes

FlashNotes is a desktop address book application used for creating and accessing flashcards for studying. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Created the feature to enter/open decks, delete decks and edit deck name.
    * What it does: 
        * Allows user to enter decks by searching for cards with tags that are equals to the deck. 
        * Users can also delete decks, which will also delete all cards in the deck.
        * Users can also edit the deck name, and the tags of the cards which have the same tag as the deck name.
    * Justifications: This features allow Users to manage their decks, and the cards inside the decks.
    * Highlights: As the cards are not implemented as a direct composition of decks, the updating of the tags of the cards had to be handled separately whenever the decks were updated or deleted.
    
* **New Feature 2**: Added the restriction of commands in Home mode and Card mode.
    * What it does: Certain commands cannot be called in the Home mode and certain commands cannot be called in the Card mode.
    * Justifications: This prevents Users from calling certain commands that were counter-intuitive so that Users can have a more pleasant experience. 
    For example, by allowing Users to add cards **only** in the card mode, users can directly see the new card added.
    * Highlights: Figuring out the design of where to put the checking of the state of the system (home mode or card mode) to ensure that it does not violate any design patterns or principles.
    
	<div style="page-break-after: always;"></div>
* **New Feature 3**: Enhancing the feature of viewing all the cards in the entire system.
    * What it does: Users can view the list of all the cards, while adding cards and deleting cards.
    * Justification: Users can conveniently see every single card, and can add, delete, edit or review all cards.
    * Highlights: In order to allow users to add cards while viewing all the cards, I had to create a pseudo deck name ('list') for the `listAll` command. I had to implement a 'Default' deck that will automatically send all cards creating while in this 'list' mode. 
    This is because all cards needed to belong to a deck, however, if a card is created in 'list' mode, then it will not belong to any deck, unless a 'Default' deck existed. The 'list' deck name is also reserved, to prevent users from creating a deck called 'list', so that the system will not be confused.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=pengxiangg&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Assigned issues in the issue tracker in Github for v1.2
    * Brainstormed ideas, implementation, features.
    * Solved merge conflicts together with the team.
    

* **Enhancements to existing features**:
    * Updated the existing CRUD features for cards to ensure that the cards and decks were linked and were working as intended.

* **Documentation**:
  * User Guide:
    * Improved the introduction of the User Guide so that Users have a clearer idea of what the User Guide is about.
    * Added a guide and explanation of how to use features such as `editDeckName`, `deleteDeck`, `listAll` and `addCard`
  * Developer Guide:
    * Wrote the explanation for the Model Component
    * Wrote the implementation of the card mode features, the list and listAll features, and the command restriction feature.
    * Updated and maintained the NFR and Glossary
    * Added new use cases and updated existing use cases to match the correct format.

* **Community**:
    * PRs reviewed (with non-trivial review comments): Examples: [1](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/146), [2](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/188)
    * PRs reviewed: Examples: [1](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/77)
