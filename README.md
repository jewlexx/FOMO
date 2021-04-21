# FOMO

Help with your FOMO when all your friends are playing on a Minecraft server, and you can't play with them

*Currently in beta for testing purposes, no new features are in the works as of now*

## Sync Minecraft and Discord chat easily

Set the server id, bot token, and easily allow people to connect with those in Discord to connect with those playing Minecraft

## Setup

### Part 1 - The Discord Side

1. First, go to https://discordapi.com/

2. From there click on Applications, and new application - *If you already have an application you want to use, obviously use that*

3. Click on the **Bot** tab on the left

4. Click add bot and "Yes, Do it!"

5. Then click the copy button under **Token** - **NOTE: DO NOT SHARE THIS WITH ANYONE EVER**

6. Keep this token safe, we will use it in the next part

7. Click on the **OAuth2** tab

8. Click copy under **Client ID**

9. Replace {CLIENT_ID} in the following link with your **Client ID** | *https://discord.com/api/oauth2/authorize?client_id={CLIENT_ID}&permissions=330752&scope=bot*

10. Follow this link and invite the bot to your desired server
    
---

### Part 2 - The Minecraft Side

1. Download the latest release from GitHub - *Should be a .jar file*

2. Paste this into the plugins folder in your server - *This assumes you have already run the server once*

3. Run the server and then close it as soon as it finishes loading

4. Go to plugins/FOMO/config.yml and open it

5. Change **token:** to the token we saved earlier

6. Change **channel:** to the id of the channel you would like the bot to watch - *Right click and press __Copy ID__*

7. Run the server again, and you're done!

Now try messaging in Discord and watch it show up in Minecraft, and vice versa

---

## Contributing

**Feel free to fork & PR**

There are no direct guidelines to modifications beyond what the **MIT** license allows but please be detailed in your PR description, or it will be ignored
