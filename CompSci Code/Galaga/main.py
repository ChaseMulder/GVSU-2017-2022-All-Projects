#!/usr/bin/env python3

import pygame as pg
import pygame.freetype
import os
from enemy import Enemy
from player import Player
from eProjectile import eProjectile
from projectile import Projectile
from pygame.locals import *
from starfield import StarField
import random


def main():
    # Startup pygame
    pg.init()

    # Get a screen object
    screen = pg.display.set_mode([1024, 768])

    # Create a player - TODO
    player = Player()

    # Create enemy and projectile Groups - TODO
    enemies = pg.sprite.Group()
    players = pg.sprite.Group()
    players.add(player)
    projectiles = pg.sprite.Group()
    projectile = Projectile
    eprojectile = eProjectile

    # Adding 70 enemies - can be changed for desired amount of enemies
    for i in range(500, 1000, 75):
        for j in range(100, 600, 50):
            enemy = Enemy((i, j))
            enemies.add(enemy)

    # Start sound - Load background music and start it
    # playing on a loop - TODO
    # note .wav because .mp3 wasn't working
    # also all the sound files and image were copied into the main folder
    # pycharm was having trouble locating images/sounds from the assets folder
    pg.mixer.music.load("cpu-talk.wav")
    pg.mixer.music.play(-1)

    # Get font setup
    pg.freetype.init()
    font_path = os.path.join(os.path.dirname(os.path.realpath(__file__)), "./assets", "PermanentMarker-Regular.ttf")
    font_size = 64
    font = pg.freetype.Font(font_path, font_size)
    # Make a tuple for FONTCOLOR - TODO
    FONTCOLOR = "orange"
    # Startup the main game loop
    running = True
    # Keep track of time
    delta = 0
    # Make sure we can't fire more than once every 250ms
    shotDelta = 250
    # Variable for enemy projectiles
    shotDelta2 = 250

    # Frame limiting
    fps = 60
    clock = pg.time.Clock()
    clock.tick(fps)
    # Setup score variable
    score = 0
    while running:

        # First thing we need to clear the events.
        for event in pg.event.get():
            if event.type == pg.QUIT:
                running = False
            if event.type == pg.USEREVENT + 1:
                score += 100

        keys = pg.key.get_pressed()

        if keys[K_s]:
            player.down(delta)
        if keys[K_w]:
            player.up(delta)
        if keys[K_SPACE]:
            if shotDelta >= .25:
                # Player shooting projectiles
                projectile = Projectile(player.rect, enemies)
                projectiles.add(projectile)
                shotDelta = 0
        # Setting up enemies firing back at player
        if shotDelta2 > 3.0:
            enemy_list2 = enemies.sprites()
            # Get random # between 1 and number of enemy ships
            ranN = random.randint(1, len(enemy_list2))
            ranN -= 1
            # Fire an enemy projectile from a randomly selected enemy
            # +70 is added inorder to make the projectile fire from the entity instead of from in front of it
            eprojectile = eProjectile(enemy_list2[ranN].rect.x + 70, enemy_list2[ranN].rect.y, player)
            shotDelta2 = 0

        # Ok, events are handled, let's update objects!
        player.update(delta)
        eprojectile.update(delta)
        for enemy in enemies:
            enemy.update(delta)
        for projectile in projectiles:
            projectile.update(delta)

        # Objects are updated, now let's draw!
        screen.fill((0, 0, 0))
        player.draw(screen)
        eprojectile.draw(screen)
        enemies.draw(screen)
        projectiles.draw(screen)
        font.render_to(screen, (10, 10), "Score: " + str(score), FONTCOLOR, None, size=64)

        # When drawing is done, flip the buffer.
        pg.display.flip()

        # How much time has passed this loop?
        delta = clock.tick(fps) / 1000.0
        shotDelta += delta
        shotDelta2 += delta

        # Check if there's enemies remaining, if not end game
        enemy_list = enemies.sprites()
        if len(enemy_list) == 0:
            running = False

        # Check if there's any players remaining, if not end game
        players_list = players.sprites()
        if eprojectile.getisPlayerHit() == True:
            running = False


# Startup the main method to get things going.
if __name__ == "__main__":
    main()
    pg.quit()
