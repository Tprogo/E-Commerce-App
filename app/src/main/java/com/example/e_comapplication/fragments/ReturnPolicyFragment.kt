package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_comapplication.R
import com.example.e_comapplication.databinding.FragmentReturnPolicyBinding


class ReturnPolicyFragment : Fragment() {

    private var _binding: FragmentReturnPolicyBinding? = null

    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentReturnPolicyBinding.inflate(layoutInflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.returnPolicyText?.text = "Return Policy\n\n" +
                "Thank you for shopping with NorthwebStar! We want to ensure you have a satisfying shopping experience with us. Please take a moment to review our return policy below:\n\n" +

                "1. Return Period:\n\n" +
                "You can return any unworn, unwashed, and undamaged item purchased from NorthwebStar within 30 days of delivery.\n\n" +

                "2. Eligibility:\n\n" +
                "To be eligible for a return, your item must be unused, with tags intact, and in the same condition that you received it. It must also be in the original packaging.\n\n" +

                "3. Reasons for Return:\n\n" +
                "You may return items due to size issues, color discrepancies, quality concerns, or if the item received is different from what you ordered.\n\n" +

                "4. Non-Returnable Items:\n\n" +
                "Certain items such as innerwear, lingerie, and items marked as non-returnable during purchase are not eligible for return due to hygiene reasons.\n\n" +

                "5. Return Process:\n\n" +
                "- Initiate a return request by logging into your NorthwebStar account.\n" +
                "- Choose the item(s) you wish to return and provide reasons for the return.\n" +
                "- Select a pickup option (if available) or drop off the package at a designated courier service.\n" +
                "- Ensure the item is securely packed with all original tags and packaging.\n\n" +

                "6. Refund/Exchange:\n\n" +
                "Once your return is received and inspected, we will send you an email to notify you that we have received your returned item. We will also notify you of the approval or rejection of your refund.\n\n" +
                "- If approved, your refund will be processed to your original method of payment within 7 days.\n" +
                "- Exchanges for a different size or color of the same item are subject to availability.\n\n" +

                "7. Cancellation Policy:\n\n" +
                "You may cancel your order before it has been shipped for a full refund. Once shipped, our standard return policy applies.\n\n" +

                "Contact Us:\n\n" +
                "If you have any questions regarding our return policy, feel free to contact our customer service team at contact@northwebstar.com"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}