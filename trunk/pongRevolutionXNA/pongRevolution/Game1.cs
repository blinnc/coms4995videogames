using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using Microsoft.Xna.Framework.Net;
using Microsoft.Xna.Framework.Storage;

namespace pongRevolution
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        Texture2D arenaBackground;
        Texture2D playerPaddle;
        KeyboardState state;
        int paddleRotation = 0;
        const int paddleSpeed = 3;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            graphics.PreferredBackBufferWidth = 1000;
            graphics.PreferredBackBufferHeight = 700;
            graphics.ApplyChanges();
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            base.Initialize();
            
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// Loads the Arena background as well as the player paddle images.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);
            arenaBackground = Content.Load<Texture2D>("images/back");
            playerPaddle = Content.Load<Texture2D>("images/blue1");
            SoundEffect song = Content.Load<SoundEffect>("sounds/game_song");
            SoundEffectInstance inst = song.CreateInstance();
            inst.IsLooped = true;
            inst.Play();

            // TODO: use this.Content to load your game content here
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        /// Checks for keyboard input (moving the paddle using 'A' and 'D'
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // TODO: Add your update logic here
            state = Keyboard.GetState();
            if(state.IsKeyDown(Keys.A))
            {
                paddleRotation += paddleSpeed;
            }
            else if (state.IsKeyDown(Keys.D))
            {
                paddleRotation -= paddleSpeed;
            }

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);
            spriteBatch.Begin();
            spriteBatch.Draw(arenaBackground, new Vector2(0, 0), Color.White);
            spriteBatch.Draw(playerPaddle, new Vector2(350, 350), null, Color.White, (float) (Math.PI * paddleRotation / 180.0), new Vector2(40, -260), 1.0f, SpriteEffects.None, 0f);
            //spriteBatch.Draw(playerPaddle, newPosition, Color.White);
            spriteBatch.End();         

            // TODO: Add your drawing code here

            base.Draw(gameTime);
        }
    }
}
