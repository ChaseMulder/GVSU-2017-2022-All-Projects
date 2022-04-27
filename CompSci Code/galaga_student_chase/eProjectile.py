import os
import pygame as pg

class eProjectile(pg.sprite.Sprite):
    def __init__(self, x, y , player):
        super(eProjectile, self).__init__()
        # Enemy Projectiles
        self.image = pg.image.load(os.path.join('assets', 'shot.png')).convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.centerx = x - 100
        self.rect.centery = y + 37
        self.player = player
        self.event = pg.USEREVENT + 1
        self.fireSound = pg.mixer.Sound("fire.wav")
        self.fireSound.play()
        self.explosionSound = pg.mixer.Sound("explosion.wav")
        self.isPlayerHit = False

    def draw(self, screen):
        screen.blit(self.image, self.rect)

    def update(self, delta):
        # Fire projectiles backwards from the enemies side of the screen
        self.rect.x -= 1000 * delta
        if self.rect.x < 0:
            self.kill()
        collision = pg.sprite.collide_rect(self, self.player)
        if collision:
            # don't kill player if hit, but end game
            self.explosionSound.play()
            self.kill()
            self.isPlayerHit = True

    def getisPlayerHit(self):
            return self.isPlayerHit