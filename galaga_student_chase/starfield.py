
import pygame
import random


LIGHTGREY = (120, 120, 120)
DARKGREY = (100, 100, 100)
YELLOW = (120, 120, 0)

# Class starfield code obtained from https://github.com/PatrickKalkman/python-galaga
class StarField():
    def __init__(self):
        self.star_field_slow = self.create_stars(50)
        self.star_field_medium = self.create_stars(35)
        self.star_field_fast = self.create_stars(30)

    def create_stars(self, number_of_stars):
        stars = []
        for _ in range(number_of_stars):
            star_loc_x = random.randrange(0, 1080)
            star_loc_y = random.randrange(0, 768)
            stars.append([star_loc_x, star_loc_y])
        return stars

    def render_stars(self, screen, star_collection, speed, size, color):
        for star in star_collection:
            star[1] += speed
            if star[1] > 768:
                star[0] = random.randrange(0, 1080)
                star[1] = random.randrange(-20, -5)
            pygame.draw.circle(screen, color, star, size)

    def render(self, screen):
        self.render_stars(screen, self.star_field_slow, 1, 3, DARKGREY)
        self.render_stars(screen, self.star_field_medium, 4, 2, LIGHTGREY)
        self.render_stars(screen, self.star_field_fast, 8, 1, YELLOW)
