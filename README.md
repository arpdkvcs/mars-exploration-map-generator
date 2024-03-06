<a name="readme-top"></a>

<div>

<h3 align="center">Mars exploration (map generator) </h3>

  <p align="center">
    A Codecool study project to create different types of game maps.
    <br />
    <a href="https://github.com/arpdkvcs/mars-exploration-map-generator/issues">Report Bug</a>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#purpose">Purpose</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#setup-and-run">Setup & Run</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This project was a task in the school (Codecool) when we just became somewhat familiar with Java.
This is a text based map generator. At the end of the interactive map setup it generates a 32x32 sized text based map.

There are 5 types of cells on the generated map:
* Flat (this is the default cell type on the map, at generation some are replaced with the rest of cell types)
* Mountain
* Pit
* Minerals
* Water

It has 2 modes (all terrain type numbers are user defined):
* Legacy:
    * Mountains and Pits are randomly placed on the map
    * Minerals are placed next to Mountains
    * Waters are placed next to pits
* Grouped:
    * Mountains and Pits are grouped together and the group number is defined by the user
    * Minerals and Waters are still placed next to Mountains and Pits respectively

The generated text map can be graphically visualized by a visualizer.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Purpose
- Practice SOLID principles.
- Write text into files.
- Structure complex algorithms into multiple steps and classes.
- Practice other design principles such as SLAP and YAGNI.

Main challenge was to figure out how to limit the placeable terrains that are defined by the user at the interactive setup.
We tried to find such a simple formula that as a result gives the user quite broad freedom at the setup and yet quite hard to give such parameters that would make the generation fail.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Java][Java]][Java-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
* This repository
* [Java][Java-url]

### Setup and Run

_Steps:_

1. Clone the repo
   ```sh
   git clone https://github.com/arpdkvcs/mars-exploration-map-generator.git
   ```
2. Run the `src/main/java/com/codecool/marsexploration/Application.java` from an IDE (Eclipse, IntelliJ) and follow the interactive setup prompts
3. When finished you can check the generated text based map in the `src/main/resources` folder where it's name is the one you gave to it during the setup (default: generated-map.map)
4. If you want to graphically visualize it then from the `src/main/resources` folder run the following command (assuming the map name is `generated-map.map`):
    ```shell
    java -jar visualizer.jar generated-map.map
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact
[![LinkedIn][linkedin-shield]][linkedin-url]

Project Link: [https://github.com/arpdkvcs/mars-exploration-map-generator](https://github.com/arpdkvcs/mars-exploration-map-generator)

<p align="right">(<a href="#readme-top">back to top</a>)</p>





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge
[linkedin-url]: https://www.linkedin.com/in/arpad-kovacs/
[Java]: https://img.shields.io/badge/Java-777777?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMjggMTI4IiBpZD0iamF2YSI+PHBhdGggZmlsbD0iIzAwNzRCRCIgZD0iTTQ3LjYxNyA5OC4xMnMtNC43NjcgMi43NzQgMy4zOTcgMy43MWM5Ljg5MiAxLjEzIDE0Ljk0Ny45NjggMjUuODQ1LTEuMDkyIDAgMCAyLjg3MSAxLjc5NSA2Ljg3MyAzLjM1MS0yNC40MzkgMTAuNDctNTUuMzA4LS42MDctMzYuMTE1LTUuOTY5ek00NC42MjkgODQuNDU1cy01LjM0OCAzLjk1OSAyLjgyMyA0LjgwNWMxMC41NjcgMS4wOTEgMTguOTEgMS4xOCAzMy4zNTQtMS42IDAgMCAxLjk5MyAyLjAyNSA1LjEzMiAzLjEzMS0yOS41NDIgOC42NC02Mi40NDYuNjgtNDEuMzA5LTYuMzM2eiI+PC9wYXRoPjxwYXRoIGZpbGw9IiNFQTJEMkUiIGQ9Ik02OS44MDIgNjEuMjcxYzYuMDI1IDYuOTM1LTEuNTggMTMuMTctMS41OCAxMy4xN3MxNS4yODktNy44OTEgOC4yNjktMTcuNzc3Yy02LjU1OS05LjIxNS0xMS41ODctMTMuNzkyIDE1LjYzNS0yOS41OCAwIC4wMDEtNDIuNzMxIDEwLjY3LTIyLjMyNCAzNC4xODd6Ij48L3BhdGg+PHBhdGggZmlsbD0iIzAwNzRCRCIgZD0iTTEwMi4xMjMgMTA4LjIyOXMzLjUyOSAyLjkxLTMuODg4IDUuMTU5Yy0xNC4xMDIgNC4yNzItNTguNzA2IDUuNTYtNzEuMDk0LjE3MS00LjQ1MS0xLjkzOCAzLjg5OS00LjYyNSA2LjUyNi01LjE5MiAyLjczOS0uNTkzIDQuMzAzLS40ODUgNC4zMDMtLjQ4NS00Ljk1My0zLjQ4Ny0zMi4wMTMgNi44NS0xMy43NDMgOS44MTUgNDkuODIxIDguMDc2IDkwLjgxNy0zLjYzNyA3Ny44OTYtOS40Njh6TTQ5LjkxMiA3MC4yOTRzLTIyLjY4NiA1LjM4OS04LjAzMyA3LjM0OGM2LjE4OC44MjggMTguNTE4LjYzOCAzMC4wMTEtLjMyNiA5LjM5LS43ODkgMTguODEzLTIuNDc0IDE4LjgxMy0yLjQ3NHMtMy4zMDggMS40MTktNS43MDQgMy4wNTNjLTIzLjA0MiA2LjA2MS02Ny41NDQgMy4yMzgtNTQuNzMxLTIuOTU4IDEwLjgzMi01LjIzOSAxOS42NDQtNC42NDMgMTkuNjQ0LTQuNjQzek05MC42MDkgOTMuMDQxYzIzLjQyMS0xMi4xNjcgMTIuNTkxLTIzLjg2IDUuMDMyLTIyLjI4NS0xLjg0OC4zODUtMi42NzcuNzItMi42NzcuNzJzLjY4OC0xLjA3OSAyLTEuNTQzYzE0Ljk1My01LjI1NSAyNi40NTEgMTUuNTAzLTQuODIzIDIzLjcyNSAwLS4wMDIuMzU5LS4zMjcuNDY4LS42MTd6Ij48L3BhdGg+PHBhdGggZmlsbD0iI0VBMkQyRSIgZD0iTTc2LjQ5MSAxLjU4N3MxMi45NjggMTIuOTc2LTEyLjMwMyAzMi45MjNjLTIwLjI2NiAxNi4wMDYtNC42MjEgMjUuMTMtLjAwNyAzNS41NTktMTEuODMxLTEwLjY3My0yMC41MDktMjAuMDctMTQuNjg4LTI4LjgxNSA4LjU0OC0xMi44MzQgMzIuMjI5LTE5LjA1OSAyNi45OTgtMzkuNjY3eiI+PC9wYXRoPjxwYXRoIGZpbGw9IiMwMDc0QkQiIGQ9Ik01Mi4yMTQgMTI2LjAyMWMyMi40NzYgMS40MzcgNTctLjggNTcuODE3LTExLjQzNiAwIDAtMS41NzEgNC4wMzItMTguNTc3IDcuMjMxLTE5LjE4NiAzLjYxMi00Mi44NTQgMy4xOTEtNTYuODg3Ljg3NCAwIC4wMDEgMi44NzUgMi4zODEgMTcuNjQ3IDMuMzMxeiI+PC9wYXRoPjwvc3ZnPg==
[Java-url]: https://www.java.com/

