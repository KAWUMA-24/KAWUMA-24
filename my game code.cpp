#include <stdio.h>
#include <conio.h> // For kbhit() and getch()
#include <stdlib.h> // For rand() and srand()
#include <time.h> // For time()vn

// Constants for grid size
const int height = 22;
const int width = 160;

// Snake properties
int snakeX[1], snakeY[1]; // Snake body coordinates
int snakeLength = 1; // Initial length
int dirX = 1, dirY = 0; // Initial direction (right)

// Fruit properties
int fruitX, fruitY;

// Score
int score = 0;

// Initialize the game
void setup() {
    srand(time(NULL)); // Seed for random number generation
    snakeX[0] = width / 2; // Initial snake position
    snakeY[0] = height / 2;
    fruitX = rand() % (width - 2) + 1; // Random fruit position
    fruitY = rand() % (height - 2) + 1;
}

// Draw the game grid
void draw() {
    system("cls"); // Clear the screen

    // Draw boundaries
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                printf("*");
            } else if (i == snakeY[0] && j == snakeX[0]) {
                printf("@"); // Snake head
            } else if (i == fruitY && j == fruitX) {
                printf("0"); // Fruit
            } else {
                int isSnakeBody = 0;
                for (int k = 1; k < snakeLength; k++) {
                    if (i == snakeY[k] && j == snakeX[k]) {
                        printf("@"); // Snake body
                        isSnakeBody = 1;
                        break;
                    }
                }
                if (!isSnakeBody) {
                    printf(" ");
                }
            }
        }
        printf("\n");
    }

    printf("Score: %d\n", score);
}

// Update snake position
void update() {
    // Move snake body
    for (int i = snakeLength - 1; i > 0; i--) {
        snakeX[i] = snakeX[i - 1];
        snakeY[i] = snakeY[i - 1];
    }

    // Move snake head
    snakeX[0] += dirX;
    snakeY[0] += dirY;

    // Check collision with boundaries
    if (snakeX[0] == 0 || snakeX[0] == width - 1 || snakeY[0] == 0 || snakeY[0] == height - 1) {
        // Game over
        printf("\nGame Over! Your score: %d\n", score);
        exit(0);
    }

    // Check collision with fruit
    if (snakeX[0] == fruitX && snakeY[0] == fruitY) {
        score++;
        snakeLength++;
        fruitX = rand() % (width - 2) + 1;
        fruitY = rand() % (height - 2) + 1;
    }
}

int main() {
    setup();

    while (1) {
        draw();
        update();

        // Get user input
        if (kbhit()) {
            char ch = getch();
            switch (ch) {
                case 'w':
                    dirX = 0; dirY = -1; // Up
                    break;
                case 's':
                    dirX = 0; dirY = 1; // Down
                    break;
                case 'a':
                    dirX = -1; dirY = 0; // Left
                    break;
                case 'd':
                    dirX = 1; dirY = 0; // Right
                    break;
                case 'x':
                    exit(0); // Quit
            }
        }
    }

    return 0;
}
