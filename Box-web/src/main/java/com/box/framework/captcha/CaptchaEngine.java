
/**
 * Project Name:PdoneCI-web
 * File Name:CaptchaEngine.java
 * Package Name:com.pdone.framework.captcha
 * Date:2017年8月7日下午6:05:58
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * ClassName:CaptchaEngine Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2017年8月7日 下午6:05:58
 * 
 * @author lucky
 * @version
 * @since JDK 1.8
 * @see
 */
public class CaptchaEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		// 图片和字体大小设置
		int minWordLength = 4;
		int maxWordLength = 4;
		int fontSize = 20;
		int imageWidth = 100;
		int imageHeight = 36;

		WordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
		// word2image components
		TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength, maxWordLength,
				new RandomListColorGenerator(
						new Color[] { new Color(23, 170, 27), new Color(220, 34, 11), new Color(23, 67, 172) }),
				new TextDecorator[] {});
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, Color.white);
		FontGenerator font = new RandomFontGenerator(fontSize, fontSize,
				new Font[] { new Font("nyala", Font.BOLD, fontSize), new Font("Bell MT", Font.PLAIN, fontSize),
						new Font("Credit valley", Font.BOLD, fontSize) });
		ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});
		WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef,
				postDef);
		addFactory(new GimpyFactory(dictionnaryWords, word2image));
	}
}
