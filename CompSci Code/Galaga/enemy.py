import os
import pygame as pg


# Complete me! - TODO

class Enemy(pg.sprite.Sprite):
    # Definition Variable that tells Enemy when to move down as to not go off the screen
    moveUp = False
    # Definition Variable to keep track of number of enemy ships
    numShips = 0

    def __init__(self, pos):
        super(Enemy, self).__init__()
        self.timer = 0
        self.interval = 2
        self.number_of_images = 1
        self.image = pg.image.load(os.path.join('assets', 'Ship1.png')).convert_alpha()
        self.surf = self.image
        self.rect = self.image.get_rect()
        self.rect.centerx = 0 + pos[0]
        self.rect.centery = 0 + pos[1]
        # Adding up number of enemy ships
        Enemy.numShips += 1


    def update(self, delta):
        if self.rect.bottom >= 768:
            Enemy.moveUp = True
        if self.rect.top <= 0:
            Enemy.moveUp = False
        if self.moveUp:
            self.rect.y -= 150 * delta
        else:
            self.rect.y += 150 * delta

    # Getter for number of enemy ships
    def getnumShips(self):
        return Enemy.numShips

