/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacegame.game.loader;

import com.spacegame.game.botpackage.Bot;
import com.spacegame.game.model.GameAPI;
import java.io.File;

/**
 *
 * @author Nikita
 */
public class ModuleEngine {

    private static Bot bot = null;

    public void loadBot(String modulePath, String moduleName, GameAPI api) {

        ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

        /**
         * Получаем список доступных модулей.
         */
        /*File dir = new File(modulePath);
        String[] modules = dir.list();
        if (modules == null) {
            System.out.println("Module path does not denote a folder");
            return;
        }*/
        /**
         * Загружаем и исполняем бота.
         */
        if (modulePath.endsWith(".class")) {
            try {
                System.out.print("Executing loading module: ");
                System.out.println(moduleName);

                Class clazz = loader.loadClass("com.spacegame.game.botpackage." + moduleName);
                bot = (Bot) clazz.newInstance();
                bot.run(api);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
