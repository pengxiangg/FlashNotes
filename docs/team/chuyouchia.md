---
layout: page
title: Jacob Chia's Project Portfolio Page
---

## Project: FlashNotes

FlashNotes is a desktop address book application used for creating and accessing flashcards for studying. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 13 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Implemented a new type of object named Decks. 
    * What it does: Enables the application to store a different type of object
    * Justification: Implementing Decks is vital to ensuring that flashcards can be organised according to themes and topics for revision purposes. It also enables the tracking of revision statistics on the deck level, which is crucial in any flashcard application.
    * Highlights: This enhancement affects existing and future commands as many actions revolves around the Deck object type. It required an in-depth analysis of design alternatives to weigh the tradeoff between the value and the speed of development. Details can be found [here.](https://ay2021s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#3-possible-designs-for-decks-in-main-mode)
    
        Implementation in particular was challenging because creating a new object affects all components from UI to Model to even storage. I handled everything end to end, which included UI, business logic and data management.
         This excludes the saving and reading of data which was done by my teammate Rachel.
  
* **New Feature 2**: Added the ability to add new Decks via addDeck Command.
<div style="page-break-after: always;"></div>  
* **New Feature 3**: Created a new UI for Home Screen to render Decks
    * What it does: Enable the user to visually separate a list of decks from list of cards
    * Justification: Having 2 visually separate screen to view flashcards in gives a sense of the hierarchical relationship between Decks and Flashcards. This separates the 2 different types of objects rendered and the logic of operating in those differing modes.
    * Highlights: Deciding to split the root nodes out from the Main screen was important in allowing scene swaps to happen. This enabled us to include review window into the card mode screen, while excluding it from the main mode.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chuyou&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=chuyouchia&tabRepo=AY2021S1-CS2103T-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)

* **Project management**:
    * Managed the release from v1.2 to v1.4 (4 releases) as the Project Lead
    * Led discussions on prioritization of issues and features and user interface
    * Set up github repo and workflows

* **Enhancements to existing features**:
    * Modified the GUI color scheme to provide visual separation between Decks and Flashcards

* **Contributions to test coverage improvement**: Pull requests ([#132](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/132/files) ,[#137](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/137/files))

* **Documentation**:
  * User Guide:
    * Wrote documentation for the features`enterDeck`, `addDeck` and `Home`
    * Standardize documentation format for features in User Guide
  * Developer Guide:
    * Updated the documentation for UI component and the [UI class diagram](https://ay2021s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#ui-component)
    * Wrote documentation for implementation of deck
    * Add implementation details of `addDeck` command including [sequence diagrams](https://ay2021s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#corresponding-sequence-diagram-for-adddeck-command)
    * Add implementation details with `FlashNotesParser` [activity diagram](https://ay2021s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#corresponding-activity-diagram-for-flashnotesparser)
    * Add activity diagram to explain the execution of `Command`
    * Add design considerations and explanation for the [3 different UIs](https://ay2021s1-cs2103t-t15-2.github.io/tp/DeveloperGuide.html#implementation-of-ui-3-different-modes) available in FlashNotes v1.4

* **Community**:
    * Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/103))
    * Reviewed teammate commits (examples: [1](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/139), [2](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/46), [3](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/71), [4](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/76), [5](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/245), [6](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/176), [7](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/108), [8](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/110))
    * Reported bugs and made suggestions for other teams in the class. Bug reports: [1](https://github.com/chuyouchia/ped/issues/1), [2](https://github.com/chuyouchia/ped/issues/2), [3](https://github.com/chuyouchia/ped/issues/3), [4](https://github.com/chuyouchia/ped/issues/4), [5](https://github.com/chuyouchia/ped/issues/5), [6](https://github.com/chuyouchia/ped/issues/6), [7](https://github.com/chuyouchia/ped/issues/7), [8](https://github.com/chuyouchia/ped/issues/8), [9](https://github.com/chuyouchia/ped/issues/9)
