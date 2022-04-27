import os
import pygame as pg
from starfield import StarField


# Create a Player class that is a subclass of pygame.sprite.Sprite
# Load an image as such:
#        self.image = pg.image.load(os.path.join('assets', 'Ship6.png')).convert_alpha()
# The position is controlled by the rectangle surrounding the image.
# Set self.rect = self.image.get_rect().  Then make changes to the 
# rectangle x, y or centerx and centery to move the object.

class Player(pg.sprite.Sprite):
    numPlayers = 0
    def __init__(self):
        super(Player, self).__init__()
        # TODO
        # Initializing background starfield
        self.starfield = StarField()
        self.timer = 0
        self.interval = 2
        self.number_of_images = 1
        self.image = pg.image.load(os.path.join('assets', 'Ship6.png')).convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.centerx = 50
        self.rect.centery =  50
        self.image_index = 0
        # Setting number of players
        Player.numPlayers += 1

    def draw(self, screen):
        screen.blit(self.image, self.rect)
        self.starfield.render(screen)

    def update(self, delta):
        pass

    def up(self, delta):
        self.rect.y -= 300 * delta

    def down(self, delta):
        self.rect.y += 300 * delta

    # Getter for number of players
    def getnumPlayers(self):
        return Player.numPlayers

