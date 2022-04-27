import os
import pygame as pg

class Projectile(pg.sprite.Sprite):
    def __init__(self, shipLocation, enemies):
        super(Projectile, self).__init__()
        # Player Projectiles
        self.image = pg.image.load(os.path.join('assets', 'shot.png')).convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.centerx = shipLocation.x + 100
        self.rect.centery = shipLocation.y + 37
        self.enemies = enemies
        self.event = pg.USEREVENT + 1
        self.fireSound = pg.mixer.Sound("fire.wav")
        self.fireSound.play()
        self.explosionSound = pg.mixer.Sound("explosion.wav")

    def draw(self, screen):
        screen.blit(self.image, self.rect)

    def update(self, delta):
        # fire projectiles forward from the player
        self.rect.x += 1000 * delta
        if self.rect.x > 1024:
            self.kill()
        collision = pg.sprite.spritecollideany(self, self.enemies)
        if collision:
            # Kill enemies if hit
            collision.kill()
            pg.event.post(pg.event.Event(self.event))
            self.explosionSound.play()
            self.kill()

